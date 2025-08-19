package datamodels

import play.api.libs.json._
import java.time.LocalDateTime

case class PaymentMethod(
  id: Option[Long] = None,
  businessId: Long,
  stripePaymentMethodId: String,
  methodType: PaymentMethodType,
  last4: Option[String] = None,
  brand: Option[String] = None,
  expMonth: Option[Int] = None,
  expYear: Option[Int] = None,
  isDefault: Boolean = false,
  createdAt: Option[LocalDateTime] = None,
  updatedAt: Option[LocalDateTime] = None
)

case class PaymentIntent(
  id: Option[Long] = None,
  businessId: Long,
  stripePaymentIntentId: String,
  amount: Long, // Amount in cents
  currency: String = "USD",
  status: PaymentIntentStatus,
  description: Option[String] = None,
  customerEmail: Option[String] = None,
  customerName: Option[String] = None,
  metadata: Option[JsValue] = None,
  createdAt: Option[LocalDateTime] = None,
  updatedAt: Option[LocalDateTime] = None
)

case class Transaction(
  id: Option[Long] = None,
  businessId: Long,
  paymentIntentId: Option[Long] = None,
  stripeChargeId: Option[String] = None,
  amount: Long, // Amount in cents
  fee: Option[Long] = None, // Stripe fee in cents
  netAmount: Option[Long] = None, // Amount minus fees
  currency: String = "USD",
  status: TransactionStatus,
  description: Option[String] = None,
  customerEmail: Option[String] = None,
  customerName: Option[String] = None,
  receiptUrl: Option[String] = None,
  createdAt: Option[LocalDateTime] = None
)

case class SubscriptionPlan(
  id: Option[Long] = None,
  businessId: Long,
  name: String,
  description: Option[String] = None,
  amount: Long, // Amount in cents
  currency: String = "USD",
  intervalType: IntervalType,
  intervalCount: Int = 1,
  stripePriceId: Option[String] = None,
  isActive: Boolean = true,
  createdAt: Option[LocalDateTime] = None,
  updatedAt: Option[LocalDateTime] = None
)

case class Subscription(
  id: Option[Long] = None,
  businessId: Long,
  customerEmail: String,
  customerName: Option[String] = None,
  planId: Long,
  stripeSubscriptionId: Option[String] = None,
  stripeCustomerId: Option[String] = None,
  status: SubscriptionStatus,
  currentPeriodStart: Option[LocalDateTime] = None,
  currentPeriodEnd: Option[LocalDateTime] = None,
  trialEnd: Option[LocalDateTime] = None,
  createdAt: Option[LocalDateTime] = None,
  updatedAt: Option[LocalDateTime] = None
)

// Enums
sealed trait PaymentMethodType
object PaymentMethodType {
  case object CARD extends PaymentMethodType
  case object BANK_ACCOUNT extends PaymentMethodType

  def fromString(value: String): PaymentMethodType = value.toLowerCase match {
    case "card" => CARD
    case "bank_account" => BANK_ACCOUNT
    case _ => CARD
  }

  def toString(methodType: PaymentMethodType): String = methodType match {
    case CARD => "card"
    case BANK_ACCOUNT => "bank_account"
  }

  implicit val paymentMethodTypeFormat: Format[PaymentMethodType] = Format(
    Reads[PaymentMethodType](js => js.validate[String].map(fromString)),
    Writes[PaymentMethodType](methodType => JsString(toString(methodType)))
  )
}

sealed trait PaymentIntentStatus
object PaymentIntentStatus {
  case object REQUIRES_PAYMENT_METHOD extends PaymentIntentStatus
  case object REQUIRES_CONFIRMATION extends PaymentIntentStatus
  case object REQUIRES_ACTION extends PaymentIntentStatus
  case object PROCESSING extends PaymentIntentStatus
  case object SUCCEEDED extends PaymentIntentStatus
  case object CANCELED extends PaymentIntentStatus

  def fromString(value: String): PaymentIntentStatus = value.toLowerCase match {
    case "requires_payment_method" => REQUIRES_PAYMENT_METHOD
    case "requires_confirmation" => REQUIRES_CONFIRMATION
    case "requires_action" => REQUIRES_ACTION
    case "processing" => PROCESSING
    case "succeeded" => SUCCEEDED
    case "canceled" => CANCELED
    case _ => REQUIRES_PAYMENT_METHOD
  }

  def toString(status: PaymentIntentStatus): String = status match {
    case REQUIRES_PAYMENT_METHOD => "requires_payment_method"
    case REQUIRES_CONFIRMATION => "requires_confirmation"
    case REQUIRES_ACTION => "requires_action"
    case PROCESSING => "processing"
    case SUCCEEDED => "succeeded"
    case CANCELED => "canceled"
  }

  implicit val paymentIntentStatusFormat: Format[PaymentIntentStatus] = Format(
    Reads[PaymentIntentStatus](js => js.validate[String].map(fromString)),
    Writes[PaymentIntentStatus](status => JsString(toString(status)))
  )
}

sealed trait TransactionStatus
object TransactionStatus {
  case object PENDING extends TransactionStatus
  case object SUCCEEDED extends TransactionStatus
  case object FAILED extends TransactionStatus
  case object REFUNDED extends TransactionStatus
  case object PARTIALLY_REFUNDED extends TransactionStatus

  def fromString(value: String): TransactionStatus = value.toLowerCase match {
    case "pending" => PENDING
    case "succeeded" => SUCCEEDED
    case "failed" => FAILED
    case "refunded" => REFUNDED
    case "partially_refunded" => PARTIALLY_REFUNDED
    case _ => PENDING
  }

  def toString(status: TransactionStatus): String = status match {
    case PENDING => "pending"
    case SUCCEEDED => "succeeded"
    case FAILED => "failed"
    case REFUNDED => "refunded"
    case PARTIALLY_REFUNDED => "partially_refunded"
  }

  implicit val transactionStatusFormat: Format[TransactionStatus] = Format(
    Reads[TransactionStatus](js => js.validate[String].map(fromString)),
    Writes[TransactionStatus](status => JsString(toString(status)))
  )
}

sealed trait IntervalType
object IntervalType {
  case object DAY extends IntervalType
  case object WEEK extends IntervalType
  case object MONTH extends IntervalType
  case object YEAR extends IntervalType

  def fromString(value: String): IntervalType = value.toLowerCase match {
    case "day" => DAY
    case "week" => WEEK
    case "month" => MONTH
    case "year" => YEAR
    case _ => MONTH
  }

  def toString(intervalType: IntervalType): String = intervalType match {
    case DAY => "day"
    case WEEK => "week"
    case MONTH => "month"
    case YEAR => "year"
  }

  implicit val intervalTypeFormat: Format[IntervalType] = Format(
    Reads[IntervalType](js => js.validate[String].map(fromString)),
    Writes[IntervalType](intervalType => JsString(toString(intervalType)))
  )
}

sealed trait SubscriptionStatus
object SubscriptionStatus {
  case object ACTIVE extends SubscriptionStatus
  case object PAST_DUE extends SubscriptionStatus
  case object CANCELED extends SubscriptionStatus
  case object UNPAID extends SubscriptionStatus
  case object TRIALING extends SubscriptionStatus

  def fromString(value: String): SubscriptionStatus = value.toLowerCase match {
    case "active" => ACTIVE
    case "past_due" => PAST_DUE
    case "canceled" => CANCELED
    case "unpaid" => UNPAID
    case "trialing" => TRIALING
    case _ => ACTIVE
  }

  def toString(status: SubscriptionStatus): String = status match {
    case ACTIVE => "active"
    case PAST_DUE => "past_due"
    case CANCELED => "canceled"
    case UNPAID => "unpaid"
    case TRIALING => "trialing"
  }

  implicit val subscriptionStatusFormat: Format[SubscriptionStatus] = Format(
    Reads[SubscriptionStatus](js => js.validate[String].map(fromString)),
    Writes[SubscriptionStatus](status => JsString(toString(status)))
  )
}

// Request/Response DTOs
case class CreatePaymentIntentRequest(
  amount: Long,
  currency: String = "USD",
  description: Option[String] = None,
  customerEmail: Option[String] = None,
  customerName: Option[String] = None,
  metadata: Option[Map[String, String]] = None
)

case class CreateSubscriptionPlanRequest(
  name: String,
  description: Option[String] = None,
  amount: Long,
  currency: String = "USD",
  intervalType: IntervalType,
  intervalCount: Int = 1
)

case class CreateSubscriptionRequest(
  planId: Long,
  customerEmail: String,
  customerName: Option[String] = None,
  paymentMethodId: Option[String] = None,
  trialDays: Option[Int] = None
)

case class RefundRequest(
  chargeId: String,
  amount: Option[Long] = None,
  reason: Option[String] = None
)

// JSON formatters
object Payment {
  implicit val paymentMethodFormat: OFormat[PaymentMethod] = Json.format[PaymentMethod]
  implicit val paymentIntentFormat: OFormat[PaymentIntent] = Json.format[PaymentIntent]
  implicit val transactionFormat: OFormat[Transaction] = Json.format[Transaction]
  implicit val subscriptionPlanFormat: OFormat[SubscriptionPlan] = Json.format[SubscriptionPlan]
  implicit val subscriptionFormat: OFormat[Subscription] = Json.format[Subscription]
  
  implicit val createPaymentIntentRequestFormat: OFormat[CreatePaymentIntentRequest] = Json.format[CreatePaymentIntentRequest]
  implicit val createSubscriptionPlanRequestFormat: OFormat[CreateSubscriptionPlanRequest] = Json.format[CreateSubscriptionPlanRequest]
  implicit val createSubscriptionRequestFormat: OFormat[CreateSubscriptionRequest] = Json.format[CreateSubscriptionRequest]
  implicit val refundRequestFormat: OFormat[RefundRequest] = Json.format[RefundRequest]
}
