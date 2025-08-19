package services

import javax.inject.{Inject, Singleton}
import play.api.Configuration
import play.api.libs.json._
import play.api.libs.ws.WSClient
import scala.concurrent.{ExecutionContext, Future}
import java.util.Base64

@Singleton
class StripeService @Inject()(config: Configuration, ws: WSClient)(implicit ec: ExecutionContext) {

  private val secretKey = config.get[String]("stripe.secretKey")
  private val baseUrl = "https://api.stripe.com/v1"
  
  private def authHeader: String = {
    val credentials = s"$secretKey:"
    "Basic " + Base64.getEncoder.encodeToString(credentials.getBytes)
  }

  case class StripePaymentIntentResult(
    id: String,
    amount: Long,
    currency: String,
    status: String,
    clientSecret: String,
    description: Option[String] = None,
    customerEmail: Option[String] = None
  )

  case class StripeCustomerResult(
    id: String,
    email: String,
    name: Option[String] = None
  )

  // JSON formatters
  implicit val stripePaymentIntentResultFormat: OFormat[StripePaymentIntentResult] = Json.format[StripePaymentIntentResult]
  implicit val stripeCustomerResultFormat: OFormat[StripeCustomerResult] = Json.format[StripeCustomerResult]

  def createPaymentIntent(
    amount: Long,
    currency: String = "USD",
    description: Option[String] = None,
    customerEmail: Option[String] = None,
    metadata: Map[String, String] = Map.empty
  ): Future[StripePaymentIntentResult] = {
    val data = Map(
      "amount" -> amount.toString,
      "currency" -> currency,
      "automatic_payment_methods[enabled]" -> "true"
    ) ++ description.map("description" -> _).toMap ++
      customerEmail.map("receipt_email" -> _).toMap ++
      metadata.map { case (k, v) => s"metadata[$k]" -> v }

    ws.url(s"$baseUrl/payment_intents")
      .withHttpHeaders(
        "Authorization" -> authHeader,
        "Content-Type" -> "application/x-www-form-urlencoded"
      )
      .post(data.map { case (k, v) => s"$k=$v" }.mkString("&"))
      .map { response =>
        if (response.status == 200) {
          val json = response.json
          StripePaymentIntentResult(
            id = (json \ "id").as[String],
            amount = (json \ "amount").as[Long],
            currency = (json \ "currency").as[String],
            status = (json \ "status").as[String],
            clientSecret = (json \ "client_secret").as[String],
            description = (json \ "description").asOpt[String],
            customerEmail = (json \ "receipt_email").asOpt[String]
          )
        } else {
          throw new RuntimeException(s"Stripe API error: ${response.status} - ${response.body}")
        }
      }
  }

  def retrievePaymentIntent(paymentIntentId: String): Future[StripePaymentIntentResult] = {
    ws.url(s"$baseUrl/payment_intents/$paymentIntentId")
      .withHttpHeaders("Authorization" -> authHeader)
      .get()
      .map { response =>
        if (response.status == 200) {
          val json = response.json
          StripePaymentIntentResult(
            id = (json \ "id").as[String],
            amount = (json \ "amount").as[Long],
            currency = (json \ "currency").as[String],
            status = (json \ "status").as[String],
            clientSecret = (json \ "client_secret").as[String],
            description = (json \ "description").asOpt[String],
            customerEmail = (json \ "receipt_email").asOpt[String]
          )
        } else {
          throw new RuntimeException(s"Stripe API error: ${response.status} - ${response.body}")
        }
      }
  }

  def createCustomer(
    email: String,
    name: Option[String] = None,
    metadata: Map[String, String] = Map.empty
  ): Future[StripeCustomerResult] = {
    val data = Map("email" -> email) ++
      name.map("name" -> _).toMap ++
      metadata.map { case (k, v) => s"metadata[$k]" -> v }

    ws.url(s"$baseUrl/customers")
      .withHttpHeaders(
        "Authorization" -> authHeader,
        "Content-Type" -> "application/x-www-form-urlencoded"
      )
      .post(data.map { case (k, v) => s"$k=$v" }.mkString("&"))
      .map { response =>
        if (response.status == 200) {
          val json = response.json
          StripeCustomerResult(
            id = (json \ "id").as[String],
            email = (json \ "email").as[String],
            name = (json \ "name").asOpt[String]
          )
        } else {
          throw new RuntimeException(s"Stripe API error: ${response.status} - ${response.body}")
        }
      }
  }

  // Simplified webhook event handling
  def handleWebhookEvent(eventType: String, eventData: JsValue): Future[JsValue] = {
    Future.successful {
      eventType match {
        case "payment_intent.succeeded" =>
          Json.obj("status" -> "processed", "event" -> eventType)
        case "payment_intent.payment_failed" =>
          Json.obj("status" -> "processed", "event" -> eventType)
        case _ =>
          Json.obj("status" -> "ignored", "event" -> eventType)
      }
    }
  }
}
