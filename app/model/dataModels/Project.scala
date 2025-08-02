package model.dataModels


case class Project(id: Option[Int] = None,
                   name: String,
                   budget: Option[Double] = None,
                   notes: Option[String] = None,
                   client_id: Int,
                   business_id: Int,
                   modified_date: Int,
                   created_date: Int,
                   is_deleted: Boolean = false)