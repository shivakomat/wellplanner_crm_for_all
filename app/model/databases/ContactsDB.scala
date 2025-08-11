package model.databases

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.Contact
import model.tools.AnormExtension._
import play.api.db.DBApi

@javax.inject.Singleton
class ContactsDB @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) with ContactsDBApi {

  private val parser: RowParser[Contact] = Macro.namedParser[Contact]

  override def addNewContact(contact: Contact): Option[Long] =
    db.withConnection { implicit connection =>
      SQL("""
          INSERT INTO contacts(name, phone, email, notes, business_id, modified_date, created_date)
          VALUES ({name}, {phone}, {email}, {notes}, {business_id}, {modified_date}, {created_date})
        """)
        .on(
          "name"          -> contact.name,
          "phone"         -> contact.phone,
          "email"         -> contact.email,
          "notes"         -> contact.notes,
          "business_id"   -> contact.business_id,
          "modified_date" -> contact.modified_date,
          "created_date"  -> contact.created_date
        ).executeInsert()
    }

  override def byId(contactId: Long): Option[Contact] =
    db.withConnection { implicit connection =>
      SQL("select * from contacts where id = {contactId}")
        .on("contactId" -> contactId)
        .as(parser.singleOpt)
    }

  override def updateContactInfo(updatedContact: Contact): Int =
    db.withConnection { implicit connection =>
      SQL("""
            UPDATE contacts SET name = {name}, phone = {phone}, email = {email}, notes = {notes}, modified_date = {modified_date}
            WHERE id = {contact_id} AND business_id = {business_id}
          """)
        .on(
          "name"          -> updatedContact.name,
          "phone"         -> updatedContact.phone,
          "email"         -> updatedContact.email,
          "notes"         -> updatedContact.notes,
          "modified_date" -> updatedContact.modified_date,
          "contact_id"    -> updatedContact.id,
          "business_id"   -> updatedContact.business_id
        ).executeUpdate()
    }

  override def list(): Seq[Contact] =
    db.withConnection { implicit connection =>
      SQL("select * from contacts").as(parser.*)
    }

  override def deleteByContactIdAndBusinessId(contactId: Int, businessId: Int): Int =
    db.withConnection { implicit connection =>
      SQL("delete from contacts where id = {contact_id} and business_id = {business_id}")
        .on("contact_id" -> contactId, "business_id" -> businessId)
        .executeUpdate()
    }
}
