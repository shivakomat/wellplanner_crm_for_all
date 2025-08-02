package model

import slick.codegen.SourceCodeGenerator

object SlickTablesGenerator extends App {

  val profile = "slick.jdbc.PostgresProfile"
  val driver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://ec2-54-88-130-244.compute-1.amazonaws.com:5432/d7vv12k8jj6go?user=muirrfptdyhuxe&password=24814dc2b91c87e790388ad51f0d6a3c826ef1f5a41093e55020dff97e91aa4a&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
  val outputFolder = "/Users/shivakomatreddy/IdeaProjects/Wedding Planner Management Software/app/model"
  val username = "muirrfptdyhuxe"
  val password = "24814dc2b91c87e790388ad51f0d6a3c826ef1f5a41093e55020dff97e91aa4a"
  val packageName = "tables"

  SourceCodeGenerator.main(
    Array(profile, driver, url, outputFolder, packageName, username, password)
  )
}
