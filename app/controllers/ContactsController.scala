package controllers

import javax.inject.{Inject, Singleton}
import controllers.util.JsonFormats._
import controllers.util.ResponseTypes.{errorResponse, successResponse}
import model.api.contacts.ContactsApi
import model.dataModels.Contact
import play.api.Logger
import play.api.db.DBApi
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class ContactsController @Inject()(dbApi: DBApi, cc: ControllerComponents, ws: WSClient) extends AbstractController(cc) {

  private val logger = Logger(this.getClass)
  private val contactsApi = new ContactsApi(dbApi)

  private def logSuccess(data: String) =
    logger.info(s"Successfully processed contact request: $data")

  private def badRequest: Future[Result] =
    Future.successful(errorResponse(BAD_REQUEST, Seq("Unable to recognize request")))

  /** Add a new contact */
  def newContact(): Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[Contact].fold(
      _ => badRequest,
      contact => contactsApi.addNewContact(contact) match {
        case Right(saved) =>
          logSuccess(Json.toJson(saved).toString())
          Future.successful(successResponse(CREATED, Json.toJson(saved), Seq("Successfully created contact")))
        case Left(msg) => Future.successful(errorResponse(FOUND, Seq(msg)))
      }
    )
  }

  /** Update contact */
  def updateContact(): Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[Contact].fold(
      _ => badRequest,
      contact => contactsApi.updateContactInfo(contact) match {
        case Right(updated) =>
          logSuccess(Json.toJson(updated).toString())
          Future.successful(successResponse(OK, Json.toJson(updated), Seq("Successfully updated contact")))
        case Left(msg) => Future.successful(errorResponse(FOUND, Seq(msg)))
      }
    )
  }

  /** List contacts by business */
  def contactsByBusiness(businessId: Int): Action[AnyContent] = Action {
    successResponse(OK, Json.toJson(contactsApi.allByBusiness(businessId)), Seq("Successfully processed"))
  }

  /** Delete contact */
  def deleteContact(contactId: Int, businessId: Int): Action[AnyContent] = Action {
    successResponse(OK, Json.toJson(contactsApi.deleteContact(contactId, businessId)), Seq("Successfully processed"))
  }
}
