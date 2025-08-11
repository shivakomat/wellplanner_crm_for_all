package model.api.contacts

import javax.inject.{Inject, Singleton}
import model.dataModels.Contact
import model.databases.{ContactsDB, ContactsDBApi}
import model.tools.DateTimeNow
import play.api.db.DBApi

/**
  * High-level service exposing CRUD operations for Contacts.
  * Thin wrapper around [[ContactsDB]] adding timestamps & simple business logic.
  */
@Singleton
class ContactsApi @Inject() (dbApi: DBApi) {

  private val contactsDb: ContactsDBApi = new ContactsDB(dbApi)

  /**
    * Insert a new contact and return the persisted entity.
    */
  def addNewContact(contact: Contact): Either[String, Contact] = {
    val now = DateTimeNow.getCurrent
    val toInsert = contact.copy(modified_date = Some(now), created_date = Some(now))
    val insertedIdOpt = contactsDb.addNewContact(toInsert)

    insertedIdOpt.flatMap(id => contactsDb.byId(id)).toRight("Failed to insert contact")
  }

  /**
    * Update an existing contact. Returns updated entity or error.
    */
  def updateContactInfo(updated: Contact): Either[String, Contact] = {
    val rows = contactsDb.updateContactInfo(updated.copy(modified_date = Some(DateTimeNow.getCurrent)))
    if (rows == 1) contactsDb.byId(updated.id.map(_.toLong).getOrElse(-1L)).toRight("Could not read updated contact")
    else Left("Update failed")
  }

  /** List all contacts for a business */
  def allByBusiness(businessId: Int): Seq[Contact] =
    contactsDb.list().filter(_.business_id == businessId)

  /** Delete a contact and return remaining list */
  def deleteContact(contactId: Int, businessId: Int): Seq[Contact] = {
    contactsDb.deleteByContactIdAndBusinessId(contactId, businessId)
    contactsDb.list()
  }
}
