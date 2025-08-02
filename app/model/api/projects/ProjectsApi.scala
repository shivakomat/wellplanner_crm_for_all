package model.api.projects

import model.api.projects.{BudgetBreakdownList, NewProjectMessage, SubBreakDownWithPayments, TaskList, TimelineItemsList}
import model.dataModels.Project
import model.databases.ProjectsDbFacade
import model.databases.ClientsDB
import model.tools.DateTimeNow
import play.api.db.DBApi

trait ProjectsApi {
  def addNewProject(newProjectMsg: NewProjectMessage): Either[String, Long]

  def allByBusiness(businessId: Int): Seq[Project]

  def deleteProjectById(projectId: Int, businessId: Int): Seq[Project]

  def getProjectById(projectId: Int, businessId: Int): Option[Project]
}


class ProjectsFacade(dbApi: DBApi) extends ProjectsApi {

  val projectsDB = new ProjectsDbFacade(dbApi)
  val clientsDB =  new ClientsDB(dbApi)

  def allByBusiness(businessId: Int): Seq[Project] =
    projectsDB.list().filter(_.business_id == businessId)


  def getProjectById(projectId: Int, businessId: Int): Option[Project] = {
    projectsDB.byId(projectId, businessId)
  }

  override def addNewProject(newProjectMsg: NewProjectMessage): Either[String, Long] = {
    val eventType = "WEDDING"
    print(newProjectMsg.toString)
    val newProject = Project(name = newProjectMsg.name,
      budget = newProjectMsg.budget,
      notes = newProjectMsg.notes,
      client_id = newProjectMsg.clientId,
      business_id = newProjectMsg.businessId,
      modified_date = DateTimeNow.getCurrent,
      created_date = DateTimeNow.getCurrent)
    val transactionResult = projectsDB.addNewProject(newProject) match {
      case Some(projectId) => Right(projectId)
      case None => Left("Unable to create the project in the database!")
    }

    if(transactionResult.isRight)
      clientsDB.byId(newProjectMsg.clientId) match {
        case Some(c) =>
          val updatedRows = clientsDB.updateBasicClientInfo(c)
          if(updatedRows == 1) transactionResult
          else Left("Failed at updating client info with the latest event date!!!")
        case None => Left("Failed to find the existing client!!!")
      }
    else transactionResult
  }

  def deleteProjectById(projectId: Int, businessId: Int): Seq[Project] = {
    val rowsDeleted = projectsDB.softDeleteByProjectIdAndBusinessId(projectId, businessId)
    projectsDB.list()
  }
}