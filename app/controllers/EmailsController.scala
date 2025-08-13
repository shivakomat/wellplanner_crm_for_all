package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.{Json, OFormat}
import services.EmailService
import scala.concurrent.ExecutionContext

@Singleton
class EmailsController @Inject()(cc: ControllerComponents, emailService: EmailService)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  case class EmailPayload(to: String, subject: String, html: String, text: Option[String])
  implicit val emailPayloadFormat: OFormat[EmailPayload] = Json.format[EmailPayload]

  def sendEmail = Action(parse.json) { request =>
    request.body.validate[EmailPayload].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid payload")),
      payload => {
        emailService.sendEmail(payload.to, payload.subject, payload.html, payload.text.getOrElse(""))
        Ok(Json.obj("status" -> "sent"))
      }
    )
  }
}
