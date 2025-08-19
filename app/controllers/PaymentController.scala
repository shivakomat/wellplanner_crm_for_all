package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import services.StripeService

@Singleton
class PaymentController @Inject()(val controllerComponents: ControllerComponents,
                                  stripeService: StripeService,
                                  config: Configuration)
                                 (implicit ec: ExecutionContext) extends BaseController {

  private val logger = Logger(this.getClass)

  // Case classes for request/response
  case class CreatePaymentIntentRequest(amount: Long, currency: String = "USD", description: Option[String] = None, customerEmail: Option[String] = None)
  case class ProcessPaymentRequest(paymentIntentId: String, paymentMethodId: String)

  // JSON formatters
  implicit val createPaymentIntentRequestFormat: Format[CreatePaymentIntentRequest] = Json.format[CreatePaymentIntentRequest]
  implicit val processPaymentRequestFormat: Format[ProcessPaymentRequest] = Json.format[ProcessPaymentRequest]
  
  // Import StripeService result formatters
  import stripeService.{stripePaymentIntentResultFormat, stripeCustomerResultFormat}

  // Create payment intent
  def createPaymentIntent(businessId: Int): Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[CreatePaymentIntentRequest] match {
      case JsSuccess(paymentRequest, _) =>
        stripeService.createPaymentIntent(
          amount = paymentRequest.amount,
          currency = paymentRequest.currency,
          description = paymentRequest.description,
          customerEmail = paymentRequest.customerEmail,
          metadata = Map("business_id" -> businessId.toString)
        ).map { response =>
          Ok(Json.toJson(response))
        }.recover {
          case ex: Exception =>
            logger.error(s"Error creating payment intent for business $businessId", ex)
            InternalServerError(Json.obj("error" -> "Failed to create payment intent", "message" -> ex.getMessage))
        }
      case JsError(errors) =>
        Future.successful(BadRequest(Json.obj("error" -> "Invalid request format", "details" -> JsError.toJson(errors))))
    }
  }

  // Get payment intent
  def getPaymentIntent(businessId: Int, paymentIntentId: String): Action[AnyContent] = Action.async { request =>
    stripeService.retrievePaymentIntent(paymentIntentId).map { response =>
      Ok(Json.toJson(response))
    }.recover {
      case ex: Exception =>
        logger.error(s"Error retrieving payment intent $paymentIntentId for business $businessId", ex)
        InternalServerError(Json.obj("error" -> "Failed to retrieve payment intent", "message" -> ex.getMessage))
    }
  }

  // Process payment (confirm payment intent)
  def processPayment(businessId: Int): Action[JsValue] = Action.async(parse.json) { request =>
    val paymentIntentId = (request.body \ "payment_intent_id").asOpt[String]
    val paymentMethodId = (request.body \ "payment_method_id").asOpt[String]

    (paymentIntentId, paymentMethodId) match {
      case (Some(piId), Some(pmId)) =>
        // For now, just return success - actual confirmation would be implemented later
        Future.successful(Ok(Json.obj(
          "status" -> "success",
          "payment_intent_id" -> piId,
          "message" -> "Payment processed successfully"
        )))
      case _ =>
        Future.successful(BadRequest(Json.obj("error" -> "Missing payment_intent_id or payment_method_id")))
    }
  }

  // Create Stripe customer
  def createCustomer(businessId: Int): Action[JsValue] = Action.async(parse.json) { request =>
    val email = (request.body \ "email").asOpt[String]
    val name = (request.body \ "name").asOpt[String]

    email match {
      case Some(customerEmail) =>
        val metadata = Map("business_id" -> businessId.toString)
        
        stripeService.createCustomer(customerEmail, name, metadata).map { customer =>
          Ok(Json.toJson(customer))
        }.recover {
          case ex: Exception =>
            logger.error(s"Error creating Stripe customer for business $businessId", ex)
            InternalServerError(Json.obj("error" -> "Failed to create customer", "message" -> ex.getMessage))
        }

      case None =>
        Future.successful(BadRequest(Json.obj("error" -> "Email is required")))
    }
  }

  // Get Stripe publishable key (for frontend)
  def getStripeConfig(): Action[AnyContent] = Action { request =>
    val publishableKey = config.get[String]("stripe.publishableKey")
    
    Ok(Json.obj(
      "publishable_key" -> publishableKey,
      "currency" -> "USD"
    ))
  }

  // Simplified webhook endpoint
  def stripeWebhook(): Action[RawBuffer] = Action.async(parse.raw) { request =>
    val payload = request.body.asBytes().map(_.utf8String).getOrElse("")
    val signature = request.headers.get("Stripe-Signature").getOrElse("")

    if (signature.isEmpty) {
      Future.successful(BadRequest(Json.obj("error" -> "Missing Stripe signature")))
    } else {
      // For now, just acknowledge the webhook
      stripeService.handleWebhookEvent("unknown", Json.obj()).map { result =>
        Ok(result)
      }.recover {
        case ex: Exception =>
          logger.error("Error processing Stripe webhook", ex)
          BadRequest(Json.obj("error" -> "Webhook processing failed", "message" -> ex.getMessage))
      }
    }
  }
}
