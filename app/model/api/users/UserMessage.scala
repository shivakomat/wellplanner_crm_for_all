package model.api.users

case class UserMessage(username: String, password: String)

case class NewUserMessage(email: String, auth0Id: String)
