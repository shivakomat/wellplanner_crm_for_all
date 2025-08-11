package model.dataModels

case class Contact(id: Option[Int] = None, name: Option[String] = None, phone: String, email: Option[String] = None, notes: Option[String] = None, business_id: Int, modified_date: Option[Int] = None, created_date: Option[Int] = None)
