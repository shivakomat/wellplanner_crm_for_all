package model.api.auth0

import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

object Auth0Credentials {
  val clientId = "BOjjaXe3n0T0TdfeCflTmT9i6ZrZiB9f"
  val connection = "well-planner-users"
}


class Auth0Client(ws: WSClient) {

  private val crossDomain = true
  private val signUpURL = "https://wellplanner.auth0.com/dbconnections/signup"

  def signUpNewUser(email: String, businessName: String, password: String): Future[WSResponse] = {
    val data = Map("client_id" -> Auth0Credentials.clientId,
                   "email" ->  email, "password" ->  password,
                   "connection" ->  Auth0Credentials.connection,
                   "name" ->  businessName)
    ws
      .url(signUpURL)
      .withHttpHeaders(("Content-Type", "application/json"))
      .post(data)
  }

}