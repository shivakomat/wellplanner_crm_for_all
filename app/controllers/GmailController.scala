package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.{Json, JsValue, OFormat}
import services.GmailService
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class GmailController @Inject()(cc: ControllerComponents, gmailService: GmailService)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  case class SendEmailRequest(to: String, subject: String, body: String)
  implicit val sendEmailFormat: OFormat[SendEmailRequest] = Json.format[SendEmailRequest]

  private def getAccessToken(request: play.api.mvc.Request[_]): Option[String] = {
    request.session.get("gmail_access_token")
  }

  def listMessages = Action.async { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        val query = request.getQueryString("q").getOrElse("")
        val maxResults = request.getQueryString("maxResults").map(_.toLong).getOrElse(50L)
        
        gmailService.listMessages(accessToken, query, maxResults.toInt)
          .map { messages =>
            Ok(Json.toJson(messages.map { msg =>
              Json.obj(
                "id" -> msg.id,
                "threadId" -> msg.threadId,
                "snippet" -> msg.snippet
              )
            }))
          }
          .recover {
            case exception => 
              InternalServerError(Json.obj("error" -> s"Failed to list messages: ${exception.getMessage}"))
          }
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Gmail not connected. Please authenticate first."))
        )
    }
  }

  def getMessage(messageId: String) = Action.async { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        gmailService.getMessage(accessToken, messageId)
          .map { message =>
            // Extract headers from payload if available
            val headers = message.payload.flatMap { payload =>
              (payload \ "headers").asOpt[List[JsValue]]
            }.getOrElse(List.empty)
            
            val subject = headers.find(h => (h \ "name").asOpt[String].contains("Subject"))
              .flatMap(h => (h \ "value").asOpt[String]).getOrElse("")
            val from = headers.find(h => (h \ "name").asOpt[String].contains("From"))
              .flatMap(h => (h \ "value").asOpt[String]).getOrElse("")
            val to = headers.find(h => (h \ "name").asOpt[String].contains("To"))
              .flatMap(h => (h \ "value").asOpt[String]).getOrElse("")
            val date = headers.find(h => (h \ "name").asOpt[String].contains("Date"))
              .flatMap(h => (h \ "value").asOpt[String]).getOrElse("")
            
            // Extract body from payload if available
            val body = message.payload.flatMap { payload =>
              (payload \ "body" \ "data").asOpt[String].map { data =>
                new String(java.util.Base64.getUrlDecoder.decode(data))
              }
            }.getOrElse("")
            
            Ok(Json.obj(
              "id" -> message.id,
              "threadId" -> message.threadId,
              "subject" -> subject,
              "from" -> from,
              "to" -> to,
              "date" -> date,
              "body" -> body,
              "snippet" -> message.snippet
            ))
          }
          .recover {
            case exception => 
              InternalServerError(Json.obj("error" -> s"Failed to get message: ${exception.getMessage}"))
          }
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Gmail not connected. Please authenticate first."))
        )
    }
  }

  def sendMessage = Action.async(parse.json) { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        request.body.validate[SendEmailRequest].fold(
          error => scala.concurrent.Future.successful(
            BadRequest(Json.obj("error" -> "Invalid request payload"))
          ),
          emailRequest => {
            gmailService.sendMessage(
              accessToken, 
              emailRequest.to, 
              emailRequest.subject, 
              emailRequest.body
            ).map { sentMessage =>
              Ok(Json.obj(
                "status" -> "sent",
                "messageId" -> sentMessage.id
              ))
            }.recover {
              case exception => 
                InternalServerError(Json.obj("error" -> s"Failed to send message: ${exception.getMessage}"))
            }
          }
        )
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Gmail not connected. Please authenticate first."))
        )
    }
  }

  def getUserProfile = Action.async { request =>
    getAccessToken(request) match {
      case Some(accessToken) =>
        gmailService.getUserProfile(accessToken)
          .map { profile =>
            Ok(Json.obj(
              "emailAddress" -> profile.emailAddress,
              "messagesTotal" -> profile.messagesTotal,
              "threadsTotal" -> profile.threadsTotal,
              "historyId" -> profile.historyId
            ))
          }
          .recover {
            case exception => 
              InternalServerError(Json.obj("error" -> s"Failed to get profile: ${exception.getMessage}"))
          }
      
      case None =>
        scala.concurrent.Future.successful(
          Unauthorized(Json.obj("error" -> "Gmail not connected. Please authenticate first."))
        )
    }
  }

  def getConnectionStatus = Action { request =>
    val isConnected = getAccessToken(request).isDefined
    Ok(Json.obj(
      "connected" -> isConnected,
      "hasRefreshToken" -> request.session.get("gmail_refresh_token").exists(_.nonEmpty)
    ))
  }
}
