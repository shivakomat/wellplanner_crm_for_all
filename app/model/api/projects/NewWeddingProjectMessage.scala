package model.api.projects

case class NewProjectMessage(name: String,
                              budget: Option[Double] = None,
                              notes: Option[String] = None,
                              businessId: Int,
                              clientId: Int)