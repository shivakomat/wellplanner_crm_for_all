package model.api.projects

import model.dataModels.Project

case class BusinessTasks(project: Project, taskLists: Seq[TaskList])
