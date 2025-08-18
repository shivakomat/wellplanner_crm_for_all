package databases

import datamodels.{IntakeForm, FormResponse, FormStatus}
import play.api.libs.json._
import javax.inject.{Inject, Singleton}
import play.api.db.Database
import java.sql.{Connection, PreparedStatement, ResultSet, Timestamp, Statement}
import java.time.LocalDateTime
import scala.util.{Try, Success, Failure}

trait IntakeFormsAPI {
  def createForm(form: IntakeForm): Try[IntakeForm]
  def getFormById(id: Long): Try[Option[IntakeForm]]
  def getFormByPublicId(publicId: String): Try[Option[IntakeForm]]
  def getFormsByBusinessId(businessId: Long): Try[List[IntakeForm]]
  def updateForm(id: Long, form: IntakeForm): Try[IntakeForm]
  def deleteForm(id: Long): Try[Boolean]
  def updateFormStatus(id: Long, status: FormStatus): Try[Boolean]
  
  def submitFormResponse(response: FormResponse): Try[FormResponse]
  def getFormResponses(formId: Long): Try[List[FormResponse]]
  def getFormResponseById(id: Long): Try[Option[FormResponse]]
  def getFormResponsesCount(formId: Long): Try[Int]
}

@Singleton
class IntakeFormsAPIImpl @Inject()(db: Database) extends IntakeFormsAPI {

  override def createForm(form: IntakeForm): Try[IntakeForm] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          INSERT INTO intake_forms (business_id, title, description, form_schema, public_id, status, created_at, updated_at)
          VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())
        """
        val stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        stmt.setLong(1, form.businessId)
        stmt.setString(2, form.title)
        stmt.setString(3, form.description.orNull)
        stmt.setString(4, Json.stringify(form.formSchema))
        stmt.setString(5, form.publicId)
        stmt.setString(6, FormStatus.toString(form.status))
        
        stmt.executeUpdate()
        val rs = stmt.getGeneratedKeys
        if (rs.next()) {
          val id = rs.getLong(1)
          form.copy(id = Some(id))
        } else {
          throw new RuntimeException("Failed to create intake form")
        }
      }
    }
  }

  override def getFormById(id: Long): Try[Option[IntakeForm]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, title, description, form_schema, public_id, status, created_at, updated_at
          FROM intake_forms WHERE id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          Some(mapResultSetToIntakeForm(rs))
        } else {
          None
        }
      }
    }
  }

  override def getFormByPublicId(publicId: String): Try[Option[IntakeForm]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, title, description, form_schema, public_id, status, created_at, updated_at
          FROM intake_forms WHERE public_id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setString(1, publicId)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          Some(mapResultSetToIntakeForm(rs))
        } else {
          None
        }
      }
    }
  }

  override def getFormsByBusinessId(businessId: Long): Try[List[IntakeForm]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, title, description, form_schema, public_id, status, created_at, updated_at
          FROM intake_forms WHERE business_id = ? ORDER BY created_at DESC
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, businessId)
        val rs = stmt.executeQuery()
        
        val forms = scala.collection.mutable.ListBuffer[IntakeForm]()
        while (rs.next()) {
          forms += mapResultSetToIntakeForm(rs)
        }
        forms.toList
      }
    }
  }

  override def updateForm(id: Long, form: IntakeForm): Try[IntakeForm] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          UPDATE intake_forms 
          SET title = ?, description = ?, form_schema = ?, status = ?, updated_at = NOW()
          WHERE id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setString(1, form.title)
        stmt.setString(2, form.description.orNull)
        stmt.setString(3, Json.stringify(form.formSchema))
        stmt.setString(4, FormStatus.toString(form.status))
        stmt.setLong(5, id)
        
        val updated = stmt.executeUpdate()
        if (updated > 0) {
          form.copy(id = Some(id))
        } else {
          throw new RuntimeException("Failed to update intake form")
        }
      }
    }
  }

  override def deleteForm(id: Long): Try[Boolean] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = "DELETE FROM intake_forms WHERE id = ?"
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, id)
        stmt.executeUpdate() > 0
      }
    }
  }

  override def updateFormStatus(id: Long, status: FormStatus): Try[Boolean] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = "UPDATE intake_forms SET status = ?, updated_at = NOW() WHERE id = ?"
        val stmt = connection.prepareStatement(sql)
        stmt.setString(1, FormStatus.toString(status))
        stmt.setLong(2, id)
        stmt.executeUpdate() > 0
      }
    }
  }

  override def submitFormResponse(response: FormResponse): Try[FormResponse] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          INSERT INTO form_responses (form_id, response_data, submitter_email, submitter_name, submitted_at, ip_address, user_agent)
          VALUES (?, ?, ?, ?, NOW(), ?, ?)
        """
        val stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        stmt.setLong(1, response.formId)
        stmt.setString(2, Json.stringify(response.responseData))
        stmt.setString(3, response.submitterEmail.orNull)
        stmt.setString(4, response.submitterName.orNull)
        stmt.setString(5, response.ipAddress.orNull)
        stmt.setString(6, response.userAgent.orNull)
        
        stmt.executeUpdate()
        val rs = stmt.getGeneratedKeys
        if (rs.next()) {
          val id = rs.getLong(1)
          response.copy(id = Some(id))
        } else {
          throw new RuntimeException("Failed to submit form response")
        }
      }
    }
  }

  override def getFormResponses(formId: Long): Try[List[FormResponse]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, form_id, response_data, submitter_email, submitter_name, submitted_at, ip_address, user_agent
          FROM form_responses WHERE form_id = ? ORDER BY submitted_at DESC
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, formId)
        val rs = stmt.executeQuery()
        
        val responses = scala.collection.mutable.ListBuffer[FormResponse]()
        while (rs.next()) {
          responses += mapResultSetToFormResponse(rs)
        }
        responses.toList
      }
    }
  }

  override def getFormResponseById(id: Long): Try[Option[FormResponse]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, form_id, response_data, submitter_email, submitter_name, submitted_at, ip_address, user_agent
          FROM form_responses WHERE id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          Some(mapResultSetToFormResponse(rs))
        } else {
          None
        }
      }
    }
  }

  override def getFormResponsesCount(formId: Long): Try[Int] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = "SELECT COUNT(*) FROM form_responses WHERE form_id = ?"
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, formId)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          rs.getInt(1)
        } else {
          0
        }
      }
    }
  }

  private def mapResultSetToIntakeForm(rs: ResultSet): IntakeForm = {
    val schemaJson = Json.parse(rs.getString("form_schema"))
    IntakeForm(
      id = Some(rs.getLong("id")),
      businessId = rs.getLong("business_id"),
      title = rs.getString("title"),
      description = Option(rs.getString("description")),
      formSchema = schemaJson,
      publicId = rs.getString("public_id"),
      status = FormStatus.fromString(rs.getString("status")),
      createdAt = Option(rs.getTimestamp("created_at").toLocalDateTime),
      updatedAt = Option(rs.getTimestamp("updated_at").toLocalDateTime)
    )
  }

  private def mapResultSetToFormResponse(rs: ResultSet): FormResponse = {
    val responseDataJson = Json.parse(rs.getString("response_data"))
    FormResponse(
      id = Some(rs.getLong("id")),
      formId = rs.getLong("form_id"),
      responseData = responseDataJson,
      submitterEmail = Option(rs.getString("submitter_email")),
      submitterName = Option(rs.getString("submitter_name")),
      submittedAt = Option(rs.getTimestamp("submitted_at").toLocalDateTime),
      ipAddress = Option(rs.getString("ip_address")),
      userAgent = Option(rs.getString("user_agent"))
    )
  }
}
