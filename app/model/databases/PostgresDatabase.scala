package model.databases

import javax.inject.Inject
import play.api.db.DBApi

@javax.inject.Singleton
class PostgresDatabase @Inject() (dbApi: DBApi) {

  protected val db = dbApi.database("default")



}
