package model.api.users

case class UserOlder(id: Int, auth0Id: String, username: String, password: String, email: String)

object Database {
  var users: Seq[UserOlder] = Seq.empty[UserOlder]
}


object Users {

  var currentId = 0

  def userExists(authOUserId: String): Boolean =
    Database.users.exists(_.auth0Id == authOUserId)

  def register(fullName: String, auth0Id: String): UserOlder = {
    currentId = currentId + 1

    val newUser = UserOlder(
      id = currentId,
      auth0Id = auth0Id,
      username = fullName,
      password = auth0Id.split('|').head,
      email = fullName + "@gmail.com" )

    Database.users = Database.users ++ Seq(newUser)
    Database.users.foreach(UserOlder => println(UserOlder.toString))
    newUser
  }

  def getByAuth0Id(auth0Id: String): Option[UserOlder] =
    Database.users.find(_.auth0Id == auth0Id)

}