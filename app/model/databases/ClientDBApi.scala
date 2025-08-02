package model.databases

import model.dataModels.Client

trait ClientDBApi {

  def addNewClient(client: Client): Option[Long]

  def byId(clientId: Long): Option[Client]

  def updateBasicClientInfo(updateClient: Client): Int

  def updateClientNotes(clientId: Int, businessId: Int, newNotes: String, modifiedDate: Int): Int

  def list(): Seq[Client]

  def deleteByClientIdAndBusinessId(clientId: Int, businessId: Int): Int

}