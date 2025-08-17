package services

import javax.inject.{Inject, Singleton}
import play.api.Configuration
import play.api.libs.ws.WSClient
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import java.util.Base64
import java.nio.charset.StandardCharsets

@Singleton
class GmailService @Inject()(ws: WSClient, config: Configuration)(implicit ec: ExecutionContext) {
  
  private val GMAIL_API_BASE = "https://gmail.googleapis.com/gmail/v1"

  case class GmailMessage(
    id: String,
    threadId: String,
    snippet: String,
    payload: Option[JsValue] = None
  )

  case class GmailProfile(
    emailAddress: String,
    messagesTotal: Long,
    threadsTotal: Long,
    historyId: String
  )

  implicit val gmailMessageFormat: OFormat[GmailMessage] = Json.format[GmailMessage]
  implicit val gmailProfileFormat: OFormat[GmailProfile] = Json.format[GmailProfile]

  def listMessages(accessToken: String, query: String = "", maxResults: Int = 50): Future[List[GmailMessage]] = {
    val url = s"$GMAIL_API_BASE/users/me/messages"
    val queryParams = Map(
      "q" -> query,
      "maxResults" -> maxResults.toString
    ).filter(_._2.nonEmpty)

    ws.url(url)
      .addHttpHeaders("Authorization" -> s"Bearer $accessToken")
      .addQueryStringParameters(queryParams.toSeq: _*)
      .get()
      .map { response =>
        if (response.status == 200) {
          (response.json \ "messages").asOpt[List[JsValue]].getOrElse(List.empty)
            .map { msgJson =>
              GmailMessage(
                id = (msgJson \ "id").as[String],
                threadId = (msgJson \ "threadId").as[String],
                snippet = ""
              )
            }
        } else {
          throw new RuntimeException(s"Gmail API error: ${response.status} - ${response.body}")
        }
      }
  }

  def getMessage(accessToken: String, messageId: String): Future[GmailMessage] = {
    val url = s"$GMAIL_API_BASE/users/me/messages/$messageId"

    ws.url(url)
      .addHttpHeaders("Authorization" -> s"Bearer $accessToken")
      .get()
      .map { response =>
        if (response.status == 200) {
          val json = response.json
          GmailMessage(
            id = (json \ "id").as[String],
            threadId = (json \ "threadId").as[String],
            snippet = (json \ "snippet").asOpt[String].getOrElse(""),
            payload = (json \ "payload").asOpt[JsValue]
          )
        } else {
          throw new RuntimeException(s"Gmail API error: ${response.status} - ${response.body}")
        }
      }
  }

  def sendMessage(accessToken: String, to: String, subject: String, bodyText: String): Future[GmailMessage] = {
    val url = s"$GMAIL_API_BASE/users/me/messages/send"
    
    // Create RFC 2822 formatted email
    val emailContent = s"""To: $to
Subject: $subject

$bodyText"""
    val encodedEmail = Base64.getUrlEncoder.encodeToString(emailContent.getBytes(StandardCharsets.UTF_8))
    
    val requestBody = Json.obj("raw" -> encodedEmail)

    ws.url(url)
      .addHttpHeaders(
        "Authorization" -> s"Bearer $accessToken",
        "Content-Type" -> "application/json"
      )
      .post(requestBody)
      .map { response =>
        if (response.status == 200) {
          val json = response.json
          GmailMessage(
            id = (json \ "id").as[String],
            threadId = (json \ "threadId").as[String],
            snippet = ""
          )
        } else {
          throw new RuntimeException(s"Gmail API error: ${response.status} - ${response.body}")
        }
      }
  }

  def getUserProfile(accessToken: String): Future[GmailProfile] = {
    val url = s"$GMAIL_API_BASE/users/me/profile"

    ws.url(url)
      .addHttpHeaders("Authorization" -> s"Bearer $accessToken")
      .get()
      .map { response =>
        if (response.status == 200) {
          val json = response.json
          GmailProfile(
            emailAddress = (json \ "emailAddress").as[String],
            messagesTotal = (json \ "messagesTotal").as[Long],
            threadsTotal = (json \ "threadsTotal").as[Long],
            historyId = (json \ "historyId").as[String]
          )
        } else {
          throw new RuntimeException(s"Gmail API error: ${response.status} - ${response.body}")
        }
      }
  }
}
