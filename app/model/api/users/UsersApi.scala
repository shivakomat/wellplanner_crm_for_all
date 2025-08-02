package model.api.users

import model.api.businesses.UsernameAndEmailCheckMessage
import model.dataModels.User

trait UsersApi {

  def userNameAndEmailCheck(username: String, email: String): UsernameAndEmailCheckMessage

  def userNameCheck(username: String): Boolean

  def emailCheck(email: String): Boolean

  def login(username: String, password: String): Option[User]

  def logOff(username: String, password: String): Option[User]

  def register(newUser: User): Option[User]

  def users(): Seq[User]

  def remove(userMessage: UserMessage): Option[String]

  def byAuth0Id(id: String): Option[User]

}
