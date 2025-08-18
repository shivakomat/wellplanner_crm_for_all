package datamodels

import play.api.libs.json._
import java.time.LocalDateTime
import java.util.UUID

case class IntakeForm(
  id: Option[Long] = None,
  businessId: Long,
  title: String,
  description: Option[String] = None,
  formSchema: JsValue,
  publicId: String = UUID.randomUUID().toString,
  status: FormStatus = FormStatus.DRAFT,
  createdAt: Option[LocalDateTime] = None,
  updatedAt: Option[LocalDateTime] = None
)

case class FormResponse(
  id: Option[Long] = None,
  formId: Long,
  responseData: JsValue,
  submitterEmail: Option[String] = None,
  submitterName: Option[String] = None,
  submittedAt: Option[LocalDateTime] = None,
  ipAddress: Option[String] = None,
  userAgent: Option[String] = None
)

sealed trait FormStatus
object FormStatus {
  case object DRAFT extends FormStatus
  case object ACTIVE extends FormStatus
  case object INACTIVE extends FormStatus

  def fromString(status: String): FormStatus = status.toUpperCase match {
    case "DRAFT" => DRAFT
    case "ACTIVE" => ACTIVE
    case "INACTIVE" => INACTIVE
    case _ => DRAFT
  }

  def toString(status: FormStatus): String = status match {
    case DRAFT => "DRAFT"
    case ACTIVE => "ACTIVE"
    case INACTIVE => "INACTIVE"
  }

  implicit val formStatusFormat: Format[FormStatus] = Format(
    Reads[FormStatus](js => js.validate[String].map(fromString)),
    Writes[FormStatus](status => JsString(toString(status)))
  )
}

// Form field types for schema validation
case class FormField(
  id: String,
  fieldType: String,
  label: String,
  required: Boolean = false,
  placeholder: Option[String] = None,
  options: Option[List[String]] = None, // For dropdown, radio, checkbox
  validation: Option[FieldValidation] = None
)

case class FieldValidation(
  minLength: Option[Int] = None,
  maxLength: Option[Int] = None,
  pattern: Option[String] = None, // Regex pattern
  min: Option[Double] = None, // For numeric fields
  max: Option[Double] = None
)

case class FormSchema(
  fields: List[FormField],
  submitButtonText: Option[String] = Some("Submit"),
  successMessage: Option[String] = Some("Thank you for your submission!"),
  redirectUrl: Option[String] = None
)

// JSON formatters
object IntakeForm {
  implicit val fieldValidationFormat: OFormat[FieldValidation] = Json.format[FieldValidation]
  implicit val formFieldFormat: OFormat[FormField] = Json.format[FormField]
  implicit val formSchemaFormat: OFormat[FormSchema] = Json.format[FormSchema]
  
  implicit val intakeFormFormat: OFormat[IntakeForm] = Json.format[IntakeForm]
  implicit val formResponseFormat: OFormat[FormResponse] = Json.format[FormResponse]
}

// Request/Response DTOs
case class CreateFormRequest(
  title: String,
  description: Option[String] = None,
  formSchema: FormSchema
)

case class UpdateFormRequest(
  title: Option[String] = None,
  description: Option[String] = None,
  formSchema: Option[FormSchema] = None,
  status: Option[FormStatus] = None
)

case class SubmitFormRequest(
  responseData: Map[String, JsValue],
  submitterEmail: Option[String] = None,
  submitterName: Option[String] = None
)

object FormRequests {
  import IntakeForm.formSchemaFormat
  implicit val createFormRequestFormat: OFormat[CreateFormRequest] = Json.format[CreateFormRequest]
  implicit val updateFormRequestFormat: OFormat[UpdateFormRequest] = Json.format[UpdateFormRequest]
  implicit val submitFormRequestFormat: OFormat[SubmitFormRequest] = Json.format[SubmitFormRequest]
}
