package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.Configuration
import scala.concurrent.ExecutionContext
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Singleton
class OAuthController @Inject()(cc: ControllerComponents, ws: WSClient, config: Configuration)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  private val clientId = config.get[String]("google.oauth.clientId")
  private val clientSecret = config.get[String]("google.oauth.clientSecret")
  private val redirectUri = config.get[String]("google.oauth.redirectUri")

  private val SCOPES = List(
    "https://www.googleapis.com/auth/gmail.readonly",
    "https://www.googleapis.com/auth/gmail.send",
    "https://www.googleapis.com/auth/gmail.compose",
    "https://www.googleapis.com/auth/calendar",
    "https://www.googleapis.com/auth/calendar.events"
  )

  def initiateGmailAuth = Action { request =>
    val userId = request.session.get("userId").getOrElse("anonymous")
    val scope = URLEncoder.encode(SCOPES.mkString(" "), StandardCharsets.UTF_8.toString)
    val state = URLEncoder.encode(userId, StandardCharsets.UTF_8.toString)
    
    val authorizationUrl = s"https://accounts.google.com/o/oauth2/v2/auth?" +
      s"client_id=$clientId&" +
      s"redirect_uri=${URLEncoder.encode(redirectUri, StandardCharsets.UTF_8.toString)}&" +
      s"scope=$scope&" +
      s"response_type=code&" +
      s"access_type=offline&" +
      s"state=$state"
    
    Redirect(authorizationUrl)
  }

  def handleCallback = Action.async { request =>
    val code = request.getQueryString("code")
    val state = request.getQueryString("state")
    
    (code, state) match {
      case (Some(authCode), Some(userId)) =>
        val tokenRequest = ws.url("https://oauth2.googleapis.com/token")
          .addHttpHeaders("Content-Type" -> "application/x-www-form-urlencoded")
          .post(Map(
            "client_id" -> clientId,
            "client_secret" -> clientSecret,
            "code" -> authCode,
            "grant_type" -> "authorization_code",
            "redirect_uri" -> redirectUri
          ).map { case (k, v) => k -> Seq(v) })
        
        tokenRequest.map { response =>
          if (response.status == 200) {
            val json = response.json
            val accessToken = (json \ "access_token").as[String]
            val refreshToken = (json \ "refresh_token").asOpt[String].getOrElse("")
            
            // Store tokens in session for now (in production, store in database)
            val newSession = request.session + 
              ("gmail_access_token" -> accessToken) +
              ("gmail_refresh_token" -> refreshToken)
            
            Redirect("/pages/dashboard/1").withSession(newSession)
              .flashing("success" -> "Gmail connected successfully!")
          } else {
            BadRequest(Json.obj("error" -> s"Token exchange failed: ${response.body}"))
          }
        }.recover {
          case e: Exception =>
            BadRequest(Json.obj("error" -> s"OAuth callback failed: ${e.getMessage}"))
        }
      
      case _ =>
        scala.concurrent.Future.successful(
          BadRequest(Json.obj("error" -> "Missing authorization code or state"))
        )
    }
  }

  def disconnectGmail = Action { request =>
    val newSession = request.session - "gmail_access_token" - "gmail_refresh_token"
    Ok(Json.obj("status" -> "disconnected")).withSession(newSession)
  }
}
