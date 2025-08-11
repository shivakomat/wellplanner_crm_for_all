package model.databases

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.Project
import model.tools.AnormExtension._
import play.api.db.DBApi

@javax.inject.Singleton
class ProjectsDbFacade @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) with ProjectsDbAPI {

  val parser: RowParser[Project] = Macro.namedParser[Project]

  def addNewProject(project: Project): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL("insert into projects(name, budget, notes, client_id, business_id, modified_date, created_date, is_deleted) " +
        "values ({name}, {budget}, {notes}, {client_id}, {business_id}, {modified_date}, {created_date}, {is_deleted})")
        .on(
          "name" -> project.name,
          "budget" -> project.budget,
          "notes" -> project.notes,
          "client_id" -> project.client_id,
          "business_id" -> project.business_id,
          "modified_date" -> project.modified_date,
          "created_date" -> project.created_date,
          "is_deleted" -> project.is_deleted
        )
        .executeInsert()
    }
  }

  def byId(projectId: Int, businessId: Int): Option[Project] =
    db.withConnection { implicit connection =>
      SQL(s"select * from projects where id = {id} and business_id = {businessId} and is_deleted = false")
        .on("id" -> projectId, "businessId" -> businessId)
        .as(parser.singleOpt)
    }


  def listByBusiness(businessId: Int): Seq[Project] =
    db.withConnection { implicit connection =>
      SQL("select * from projects where business_id = {businessId} and is_deleted = false")
        .on("businessId" -> businessId)
        .as(parser.*)
    }

  def list(): Seq[Project] =
    db.withConnection { implicit connection =>
      SQL("select * from projects where is_deleted = false").as(parser.*)
    }

  def softDeleteByProjectIdAndBusinessId(projectId: Int, businessId: Int): Int =
    db.withConnection { implicit connection =>
      SQL("update projects set is_deleted = true where id = {project_id} and business_id = {business_id}")
        .on("project_id" -> projectId, "business_id" -> businessId)
        .executeUpdate()
    }
}
