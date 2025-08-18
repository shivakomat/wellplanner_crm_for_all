package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents, Request}
import play.api.libs.json._
import databases.IntakeFormsAPI
import datamodels.{IntakeForm, FormResponse, FormStatus, CreateFormRequest, UpdateFormRequest, SubmitFormRequest}
import datamodels.FormRequests._
import datamodels.IntakeForm._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Failure}
import java.util.UUID

@Singleton
class IntakeFormsController @Inject()(
  cc: ControllerComponents,
  intakeFormsAPI: IntakeFormsAPI
)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  // Get all forms for a business
  def getFormsByBusiness(businessId: Long) = Action.async { implicit request =>
    Future {
      intakeFormsAPI.getFormsByBusinessId(businessId) match {
        case Success(forms) =>
          Ok(Json.obj(
            "status" -> "success",
            "forms" -> forms.map(formToJson)
          ))
        case Failure(exception) =>
          InternalServerError(Json.obj(
            "status" -> "error",
            "message" -> s"Failed to retrieve forms: ${exception.getMessage}"
          ))
      }
    }
  }

  // Get specific form by ID
  def getForm(businessId: Long, formId: Long) = Action.async { implicit request =>
    Future {
      intakeFormsAPI.getFormById(formId) match {
        case Success(Some(form)) if form.businessId == businessId =>
          Ok(Json.obj(
            "status" -> "success",
            "form" -> formToJson(form)
          ))
        case Success(Some(_)) =>
          Forbidden(Json.obj(
            "status" -> "error",
            "message" -> "Access denied to this form"
          ))
        case Success(None) =>
          NotFound(Json.obj(
            "status" -> "error",
            "message" -> "Form not found"
          ))
        case Failure(exception) =>
          InternalServerError(Json.obj(
            "status" -> "error",
            "message" -> s"Failed to retrieve form: ${exception.getMessage}"
          ))
      }
    }
  }

  // Create new form
  def createForm(businessId: Long) = Action.async(parse.json) { implicit request =>
    Future {
      request.body.validate[CreateFormRequest].fold(
        _ => BadRequest(Json.obj(
          "status" -> "error",
          "message" -> "Invalid form request payload"
        )),
        createRequest => {
          val form = IntakeForm(
            businessId = businessId,
            title = createRequest.title,
            description = createRequest.description,
            formSchema = Json.toJson(createRequest.formSchema),
            publicId = UUID.randomUUID().toString,
            status = FormStatus.DRAFT
          )

          intakeFormsAPI.createForm(form) match {
            case Success(createdForm) =>
              Created(Json.obj(
                "status" -> "success",
                "message" -> "Form created successfully",
                "form" -> formToJson(createdForm)
              ))
            case Failure(exception) =>
              InternalServerError(Json.obj(
                "status" -> "error",
                "message" -> s"Failed to create form: ${exception.getMessage}"
              ))
          }
        }
      )
    }
  }

  // Update existing form
  def updateForm(businessId: Long, formId: Long) = Action.async(parse.json) { implicit request =>
    Future {
      request.body.validate[UpdateFormRequest].fold(
        _ => BadRequest(Json.obj(
          "status" -> "error",
          "message" -> "Invalid update request payload"
        )),
        updateRequest => {
          intakeFormsAPI.getFormById(formId) match {
            case Success(Some(existingForm)) if existingForm.businessId == businessId =>
              val updatedForm = existingForm.copy(
                title = updateRequest.title.getOrElse(existingForm.title),
                description = updateRequest.description.orElse(existingForm.description),
                formSchema = updateRequest.formSchema.map(Json.toJson(_)).getOrElse(existingForm.formSchema),
                status = updateRequest.status.getOrElse(existingForm.status)
              )

              intakeFormsAPI.updateForm(formId, updatedForm) match {
                case Success(updated) =>
                  Ok(Json.obj(
                    "status" -> "success",
                    "message" -> "Form updated successfully",
                    "form" -> formToJson(updated)
                  ))
                case Failure(exception) =>
                  InternalServerError(Json.obj(
                    "status" -> "error",
                    "message" -> s"Failed to update form: ${exception.getMessage}"
                  ))
              }
            case Success(Some(_)) =>
              Forbidden(Json.obj(
                "status" -> "error",
                "message" -> "Access denied to this form"
              ))
            case Success(None) =>
              NotFound(Json.obj(
                "status" -> "error",
                "message" -> "Form not found"
              ))
            case Failure(exception) =>
              InternalServerError(Json.obj(
                "status" -> "error",
                "message" -> s"Failed to retrieve form: ${exception.getMessage}"
              ))
          }
        }
      )
    }
  }

  // Delete form
  def deleteForm(businessId: Long, formId: Long) = Action.async { implicit request =>
    Future {
      intakeFormsAPI.getFormById(formId) match {
        case Success(Some(form)) if form.businessId == businessId =>
          intakeFormsAPI.deleteForm(formId) match {
            case Success(true) =>
              Ok(Json.obj(
                "status" -> "success",
                "message" -> "Form deleted successfully"
              ))
            case Success(false) =>
              NotFound(Json.obj(
                "status" -> "error",
                "message" -> "Form not found"
              ))
            case Failure(exception) =>
              InternalServerError(Json.obj(
                "status" -> "error",
                "message" -> s"Failed to delete form: ${exception.getMessage}"
              ))
          }
        case Success(Some(_)) =>
          Forbidden(Json.obj(
            "status" -> "error",
            "message" -> "Access denied to this form"
          ))
        case Success(None) =>
          NotFound(Json.obj(
            "status" -> "error",
            "message" -> "Form not found"
          ))
        case Failure(exception) =>
          InternalServerError(Json.obj(
            "status" -> "error",
            "message" -> s"Failed to retrieve form: ${exception.getMessage}"
          ))
      }
    }
  }

  // Update form status
  def updateFormStatus(businessId: Long, formId: Long) = Action.async(parse.json) { implicit request =>
    Future {
      (request.body \ "status").asOpt[String] match {
        case Some(statusStr) =>
          val status = FormStatus.fromString(statusStr)
          intakeFormsAPI.getFormById(formId) match {
            case Success(Some(form)) if form.businessId == businessId =>
              intakeFormsAPI.updateFormStatus(formId, status) match {
                case Success(true) =>
                  Ok(Json.obj(
                    "status" -> "success",
                    "message" -> "Form status updated successfully"
                  ))
                case Success(false) =>
                  NotFound(Json.obj(
                    "status" -> "error",
                    "message" -> "Form not found"
                  ))
                case Failure(exception) =>
                  InternalServerError(Json.obj(
                    "status" -> "error",
                    "message" -> s"Failed to update form status: ${exception.getMessage}"
                  ))
              }
            case Success(Some(_)) =>
              Forbidden(Json.obj(
                "status" -> "error",
                "message" -> "Access denied to this form"
              ))
            case Success(None) =>
              NotFound(Json.obj(
                "status" -> "error",
                "message" -> "Form not found"
              ))
            case Failure(exception) =>
              InternalServerError(Json.obj(
                "status" -> "error",
                "message" -> s"Failed to retrieve form: ${exception.getMessage}"
              ))
          }
        case None =>
          BadRequest(Json.obj(
            "status" -> "error",
            "message" -> "Status field is required"
          ))
      }
    }
  }

  // PUBLIC ENDPOINTS - No authentication required

  // Get public form by public ID (for form rendering)
  def getPublicForm(publicId: String) = Action.async { implicit request =>
    Future {
      intakeFormsAPI.getFormByPublicId(publicId) match {
        case Success(Some(form)) if form.status == FormStatus.ACTIVE =>
          Ok(Json.obj(
            "status" -> "success",
            "form" -> Json.obj(
              "id" -> form.publicId,
              "title" -> form.title,
              "description" -> form.description,
              "schema" -> form.formSchema
            )
          ))
        case Success(Some(_)) =>
          NotFound(Json.obj(
            "status" -> "error",
            "message" -> "Form is not available"
          ))
        case Success(None) =>
          NotFound(Json.obj(
            "status" -> "error",
            "message" -> "Form not found"
          ))
        case Failure(exception) =>
          InternalServerError(Json.obj(
            "status" -> "error",
            "message" -> s"Failed to retrieve form: ${exception.getMessage}"
          ))
      }
    }
  }

  // Submit form response (public endpoint)
  def submitForm(publicId: String) = Action.async(parse.json) { implicit request =>
    Future {
      request.body.validate[SubmitFormRequest].fold(
        _ => BadRequest(Json.obj(
          "status" -> "error",
          "message" -> "Invalid submission payload"
        )),
        submitRequest => {
          intakeFormsAPI.getFormByPublicId(publicId) match {
            case Success(Some(form)) if form.status == FormStatus.ACTIVE =>
              val response = FormResponse(
                formId = form.id.get,
                responseData = Json.toJson(submitRequest.responseData),
                submitterEmail = submitRequest.submitterEmail,
                submitterName = submitRequest.submitterName,
                ipAddress = getClientIP(request),
                userAgent = request.headers.get("User-Agent")
              )

              intakeFormsAPI.submitFormResponse(response) match {
                case Success(submittedResponse) =>
                  Ok(Json.obj(
                    "status" -> "success",
                    "message" -> "Form submitted successfully",
                    "submissionId" -> submittedResponse.id
                  ))
                case Failure(exception) =>
                  InternalServerError(Json.obj(
                    "status" -> "error",
                    "message" -> s"Failed to submit form: ${exception.getMessage}"
                  ))
              }
            case Success(Some(_)) =>
              BadRequest(Json.obj(
                "status" -> "error",
                "message" -> "Form is not available for submissions"
              ))
            case Success(None) =>
              NotFound(Json.obj(
                "status" -> "error",
                "message" -> "Form not found"
              ))
            case Failure(exception) =>
              InternalServerError(Json.obj(
                "status" -> "error",
                "message" -> s"Failed to retrieve form: ${exception.getMessage}"
              ))
          }
        }
      )
    }
  }

  // Get form responses (for business owners)
  def getFormResponses(businessId: Long, formId: Long) = Action.async { implicit request =>
    Future {
      intakeFormsAPI.getFormById(formId) match {
        case Success(Some(form)) if form.businessId == businessId =>
          intakeFormsAPI.getFormResponses(formId) match {
            case Success(responses) =>
              Ok(Json.obj(
                "status" -> "success",
                "responses" -> responses.map(responseToJson)
              ))
            case Failure(exception) =>
              InternalServerError(Json.obj(
                "status" -> "error",
                "message" -> s"Failed to retrieve responses: ${exception.getMessage}"
              ))
          }
        case Success(Some(_)) =>
          Forbidden(Json.obj(
            "status" -> "error",
            "message" -> "Access denied to this form"
          ))
        case Success(None) =>
          NotFound(Json.obj(
            "status" -> "error",
            "message" -> "Form not found"
          ))
        case Failure(exception) =>
          InternalServerError(Json.obj(
            "status" -> "error",
            "message" -> s"Failed to retrieve form: ${exception.getMessage}"
          ))
      }
    }
  }

  // Helper methods
  private def formToJson(form: IntakeForm): JsObject = {
    Json.obj(
      "id" -> form.id,
      "businessId" -> form.businessId,
      "title" -> form.title,
      "description" -> form.description,
      "formSchema" -> form.formSchema,
      "publicId" -> form.publicId,
      "status" -> FormStatus.toString(form.status),
      "createdAt" -> form.createdAt,
      "updatedAt" -> form.updatedAt,
      "publicUrl" -> s"/forms/${form.publicId}"
    )
  }

  private def responseToJson(response: FormResponse): JsObject = {
    Json.obj(
      "id" -> response.id,
      "formId" -> response.formId,
      "responseData" -> response.responseData,
      "submitterEmail" -> response.submitterEmail,
      "submitterName" -> response.submitterName,
      "submittedAt" -> response.submittedAt,
      "ipAddress" -> response.ipAddress
    )
  }

  private def getClientIP(request: Request[_]): Option[String] = {
    request.headers.get("X-Forwarded-For")
      .orElse(request.headers.get("X-Real-IP"))
      .orElse(Some(request.remoteAddress))
  }
}
