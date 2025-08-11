package model.databases

import model.dataModels.Contact

trait ContactsDBApi {

  /**
    * Insert a new contact and return generated id (if any)
    */
  def addNewContact(contact: Contact): Option[Long]

  /**
    * Lookup a contact by its id
    */
  def byId(contactId: Long): Option[Contact]

  /**
    * Update the basic information of a contact. Returns affected row count.
    */
  def updateContactInfo(updatedContact: Contact): Int

  /**
    * Return all contacts
    */
  def list(): Seq[Contact]

  /**
    * Delete a contact scoped to business id. Returns affected row count.
    */
  def deleteByContactIdAndBusinessId(contactId: Int, businessId: Int): Int
}
