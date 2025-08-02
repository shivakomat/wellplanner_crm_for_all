package model.tables
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Businesses.schema, Customers.schema, PlayEvolutions.schema, Projects.schema, Taskcomments.schema, Tasks.schema, Users.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Businesses
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(100,true), Default(None)
   *  @param city Database column city SqlType(varchar), Length(100,true), Default(None)
   *  @param state Database column state SqlType(varchar), Length(100,true), Default(None)
   *  @param country Database column country SqlType(varchar), Length(100,true), Default(None)
   *  @param modifieddate Database column modifiedDate SqlType(timestamp), Default(None)
   *  @param createddate Database column createdDate SqlType(timestamp), Default(None) */
  case class BusinessesRow(id: Int, name: Option[String] = None, city: Option[String] = None, state: Option[String] = None, country: Option[String] = None, modifieddate: Option[java.sql.Timestamp] = None, createddate: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching BusinessesRow objects using plain SQL queries */
  implicit def GetResultBusinessesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[BusinessesRow] = GR{
    prs => import prs._
    BusinessesRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table businesses. Objects of this class serve as prototypes for rows in queries. */
  class Businesses(_tableTag: Tag) extends profile.api.Table[BusinessesRow](_tableTag, "businesses") {
    def * = (id, name, city, state, country, modifieddate, createddate) <> (BusinessesRow.tupled, BusinessesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, city, state, country, modifieddate, createddate).shaped.<>({r=>import r._; _1.map(_=> BusinessesRow.tupled((_1.get, _2, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(100,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(100,varying=true), O.Default(None))
    /** Database column city SqlType(varchar), Length(100,true), Default(None) */
    val city: Rep[Option[String]] = column[Option[String]]("city", O.Length(100,varying=true), O.Default(None))
    /** Database column state SqlType(varchar), Length(100,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(100,varying=true), O.Default(None))
    /** Database column country SqlType(varchar), Length(100,true), Default(None) */
    val country: Rep[Option[String]] = column[Option[String]]("country", O.Length(100,varying=true), O.Default(None))
    /** Database column modifiedDate SqlType(timestamp), Default(None) */
    val modifieddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("modifiedDate", O.Default(None))
    /** Database column createdDate SqlType(timestamp), Default(None) */
    val createddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("createdDate", O.Default(None))
  }
  /** Collection-like TableQuery object for table Businesses */
  lazy val Businesses = new TableQuery(tag => new Businesses(tag))

  /** Entity class storing rows of table Customers
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(50,true), Default(None)
   *  @param eventtype Database column eventType SqlType(varchar), Length(25,true), Default(None)
   *  @param email Database column email SqlType(varchar), Length(100,true), Default(None)
   *  @param notes Database column notes SqlType(varchar), Length(1024,true), Default(None)
   *  @param budget Database column budget SqlType(float8), Default(None)
   *  @param status Database column status SqlType(varchar), Length(100,true), Default(None)
   *  @param businessid Database column businessId SqlType(serial), AutoInc
   *  @param modifieddate Database column modifiedDate SqlType(timestamp), Default(None)
   *  @param createddate Database column createdDate SqlType(timestamp), Default(None) */
  final case class CustomersRow(id: Int, name: Option[String] = None, eventtype: Option[String] = None, email: Option[String] = None, notes: Option[String] = None, budget: Option[Double] = None, status: Option[String] = None, businessid: Int, modifieddate: Option[java.sql.Timestamp] = None, createddate: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching CustomersRow objects using plain SQL queries */
  implicit def GetResultCustomersRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Double]], e3: GR[Option[java.sql.Timestamp]]): GR[CustomersRow] = GR{
    prs => import prs._
    CustomersRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Double], <<?[String], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table customers. Objects of this class serve as prototypes for rows in queries. */
  class Customers(_tableTag: Tag) extends profile.api.Table[CustomersRow](_tableTag, "customers") {
    def * = (id, name, eventtype, email, notes, budget, status, businessid, modifieddate, createddate) <> (CustomersRow.tupled, CustomersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, eventtype, email, notes, budget, status, Rep.Some(businessid), modifieddate, createddate).shaped.<>({r=>import r._; _1.map(_=> CustomersRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8.get, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(50,varying=true), O.Default(None))
    /** Database column eventType SqlType(varchar), Length(25,true), Default(None) */
    val eventtype: Rep[Option[String]] = column[Option[String]]("eventType", O.Length(25,varying=true), O.Default(None))
    /** Database column email SqlType(varchar), Length(100,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(100,varying=true), O.Default(None))
    /** Database column notes SqlType(varchar), Length(1024,true), Default(None) */
    val notes: Rep[Option[String]] = column[Option[String]]("notes", O.Length(1024,varying=true), O.Default(None))
    /** Database column budget SqlType(float8), Default(None) */
    val budget: Rep[Option[Double]] = column[Option[Double]]("budget", O.Default(None))
    /** Database column status SqlType(varchar), Length(100,true), Default(None) */
    val status: Rep[Option[String]] = column[Option[String]]("status", O.Length(100,varying=true), O.Default(None))
    /** Database column businessId SqlType(serial), AutoInc */
    val businessid: Rep[Int] = column[Int]("businessId", O.AutoInc)
    /** Database column modifiedDate SqlType(timestamp), Default(None) */
    val modifieddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("modifiedDate", O.Default(None))
    /** Database column createdDate SqlType(timestamp), Default(None) */
    val createddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("createdDate", O.Default(None))

    /** Foreign key referencing Businesses (database name customers_businessId_fkey) */
    lazy val businessesFk = foreignKey("customers_businessId_fkey", businessid, Businesses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Customers */
  lazy val Customers = new TableQuery(tag => new Customers(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param hash Database column hash SqlType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(timestamp)
   *  @param applyScript Database column apply_script SqlType(text), Default(None)
   *  @param revertScript Database column revert_script SqlType(text), Default(None)
   *  @param state Database column state SqlType(varchar), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(text), Default(None) */
  final case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(text), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Default(None))
    /** Database column revert_script SqlType(text), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Default(None))
    /** Database column state SqlType(varchar), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(text), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Projects
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(50,true), Default(None)
   *  @param eventtype Database column eventType SqlType(varchar), Length(25,true), Default(None)
   *  @param bridesname Database column bridesName SqlType(varchar), Length(100,true), Default(None)
   *  @param groomsname Database column groomsName SqlType(varchar), Length(100,true), Default(None)
   *  @param notes Database column notes SqlType(varchar), Length(1024,true), Default(None)
   *  @param budget Database column budget SqlType(float8), Default(None)
   *  @param businessid Database column businessId SqlType(serial), AutoInc
   *  @param customerid Database column customerId SqlType(serial), AutoInc
   *  @param modifieddate Database column modifiedDate SqlType(timestamp), Default(None)
   *  @param createddate Database column createdDate SqlType(timestamp), Default(None) */
  final case class ProjectsRow(id: Int, name: Option[String] = None, eventtype: Option[String] = None, bridesname: Option[String] = None, groomsname: Option[String] = None, notes: Option[String] = None, budget: Option[Double] = None, businessid: Int, customerid: Int, modifieddate: Option[java.sql.Timestamp] = None, createddate: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching ProjectsRow objects using plain SQL queries */
  implicit def GetResultProjectsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Double]], e3: GR[Option[java.sql.Timestamp]]): GR[ProjectsRow] = GR{
    prs => import prs._
    ProjectsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Double], <<[Int], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table projects. Objects of this class serve as prototypes for rows in queries. */
  class Projects(_tableTag: Tag) extends profile.api.Table[ProjectsRow](_tableTag, "projects") {
    def * = (id, name, eventtype, bridesname, groomsname, notes, budget, businessid, customerid, modifieddate, createddate) <> (ProjectsRow.tupled, ProjectsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, eventtype, bridesname, groomsname, notes, budget, Rep.Some(businessid), Rep.Some(customerid), modifieddate, createddate).shaped.<>({r=>import r._; _1.map(_=> ProjectsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8.get, _9.get, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(50,varying=true), O.Default(None))
    /** Database column eventType SqlType(varchar), Length(25,true), Default(None) */
    val eventtype: Rep[Option[String]] = column[Option[String]]("eventType", O.Length(25,varying=true), O.Default(None))
    /** Database column bridesName SqlType(varchar), Length(100,true), Default(None) */
    val bridesname: Rep[Option[String]] = column[Option[String]]("bridesName", O.Length(100,varying=true), O.Default(None))
    /** Database column groomsName SqlType(varchar), Length(100,true), Default(None) */
    val groomsname: Rep[Option[String]] = column[Option[String]]("groomsName", O.Length(100,varying=true), O.Default(None))
    /** Database column notes SqlType(varchar), Length(1024,true), Default(None) */
    val notes: Rep[Option[String]] = column[Option[String]]("notes", O.Length(1024,varying=true), O.Default(None))
    /** Database column budget SqlType(float8), Default(None) */
    val budget: Rep[Option[Double]] = column[Option[Double]]("budget", O.Default(None))
    /** Database column businessId SqlType(serial), AutoInc */
    val businessid: Rep[Int] = column[Int]("businessId", O.AutoInc)
    /** Database column customerId SqlType(serial), AutoInc */
    val customerid: Rep[Int] = column[Int]("customerId", O.AutoInc)
    /** Database column modifiedDate SqlType(timestamp), Default(None) */
    val modifieddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("modifiedDate", O.Default(None))
    /** Database column createdDate SqlType(timestamp), Default(None) */
    val createddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("createdDate", O.Default(None))

    /** Foreign key referencing Businesses (database name projects_businessId_fkey) */
    lazy val businessesFk = foreignKey("projects_businessId_fkey", businessid, Businesses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Customers (database name projects_customerId_fkey) */
    lazy val customersFk = foreignKey("projects_customerId_fkey", customerid, Customers)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Projects */
  lazy val Projects = new TableQuery(tag => new Projects(tag))

  /** Entity class storing rows of table Taskcomments
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param commenttext Database column commentText SqlType(varchar), Length(1024,true), Default(None)
   *  @param usercreatedid Database column userCreatedID SqlType(serial), AutoInc
   *  @param taskid Database column taskId SqlType(serial), AutoInc
   *  @param businessid Database column businessId SqlType(serial), AutoInc
   *  @param projectid Database column projectId SqlType(serial), AutoInc
   *  @param modifieddate Database column modifiedDate SqlType(timestamp), Default(None)
   *  @param createddate Database column createdDate SqlType(timestamp), Default(None) */
  final case class TaskcommentsRow(id: Int, commenttext: Option[String] = None, usercreatedid: Int, taskid: Int, businessid: Int, projectid: Int, modifieddate: Option[java.sql.Timestamp] = None, createddate: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching TaskcommentsRow objects using plain SQL queries */
  implicit def GetResultTaskcommentsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[TaskcommentsRow] = GR{
    prs => import prs._
    TaskcommentsRow.tupled((<<[Int], <<?[String], <<[Int], <<[Int], <<[Int], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table taskComments. Objects of this class serve as prototypes for rows in queries. */
  class Taskcomments(_tableTag: Tag) extends profile.api.Table[TaskcommentsRow](_tableTag, "taskComments") {
    def * = (id, commenttext, usercreatedid, taskid, businessid, projectid, modifieddate, createddate) <> (TaskcommentsRow.tupled, TaskcommentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), commenttext, Rep.Some(usercreatedid), Rep.Some(taskid), Rep.Some(businessid), Rep.Some(projectid), modifieddate, createddate).shaped.<>({r=>import r._; _1.map(_=> TaskcommentsRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6.get, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column commentText SqlType(varchar), Length(1024,true), Default(None) */
    val commenttext: Rep[Option[String]] = column[Option[String]]("commentText", O.Length(1024,varying=true), O.Default(None))
    /** Database column userCreatedID SqlType(serial), AutoInc */
    val usercreatedid: Rep[Int] = column[Int]("userCreatedID", O.AutoInc)
    /** Database column taskId SqlType(serial), AutoInc */
    val taskid: Rep[Int] = column[Int]("taskId", O.AutoInc)
    /** Database column businessId SqlType(serial), AutoInc */
    val businessid: Rep[Int] = column[Int]("businessId", O.AutoInc)
    /** Database column projectId SqlType(serial), AutoInc */
    val projectid: Rep[Int] = column[Int]("projectId", O.AutoInc)
    /** Database column modifiedDate SqlType(timestamp), Default(None) */
    val modifieddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("modifiedDate", O.Default(None))
    /** Database column createdDate SqlType(timestamp), Default(None) */
    val createddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("createdDate", O.Default(None))

    /** Foreign key referencing Businesses (database name taskComments_businessId_fkey) */
    lazy val businessesFk = foreignKey("taskComments_businessId_fkey", businessid, Businesses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Projects (database name taskComments_projectId_fkey) */
    lazy val projectsFk = foreignKey("taskComments_projectId_fkey", projectid, Projects)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Tasks (database name taskComments_taskId_fkey) */
    lazy val tasksFk = foreignKey("taskComments_taskId_fkey", taskid, Tasks)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Users (database name taskComments_userCreatedID_fkey) */
    lazy val usersFk = foreignKey("taskComments_userCreatedID_fkey", usercreatedid, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Taskcomments */
  lazy val Taskcomments = new TableQuery(tag => new Taskcomments(tag))

  /** Entity class storing rows of table Tasks
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param title Database column title SqlType(varchar), Length(1024,true), Default(None)
   *  @param description Database column description SqlType(varchar), Length(1024,true), Default(None)
   *  @param notes Database column notes SqlType(varchar), Length(1024,true), Default(None)
   *  @param iscategory Database column isCategory SqlType(bool), Default(None)
   *  @param isvisibletocustomer Database column isVisibleToCustomer SqlType(bool), Default(None)
   *  @param duedate Database column dueDate SqlType(timestamp), Default(None)
   *  @param assigneduserid Database column assignedUserId SqlType(serial), AutoInc
   *  @param usercreatedid Database column userCreatedID SqlType(serial), AutoInc
   *  @param businessid Database column businessId SqlType(serial), AutoInc
   *  @param projectid Database column projectId SqlType(serial), AutoInc
   *  @param parenttaskid Database column parentTaskId SqlType(serial), AutoInc
   *  @param modifieddate Database column modifiedDate SqlType(timestamp), Default(None)
   *  @param createddate Database column createdDate SqlType(timestamp), Default(None) */
  final case class TasksRow(id: Int, title: Option[String] = None, description: Option[String] = None, notes: Option[String] = None, iscategory: Option[Boolean] = None, isvisibletocustomer: Option[Boolean] = None, duedate: Option[java.sql.Timestamp] = None, assigneduserid: Int, usercreatedid: Int, businessid: Int, projectid: Int, parenttaskid: Int, modifieddate: Option[java.sql.Timestamp] = None, createddate: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching TasksRow objects using plain SQL queries */
  implicit def GetResultTasksRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Boolean]], e3: GR[Option[java.sql.Timestamp]]): GR[TasksRow] = GR{
    prs => import prs._
    TasksRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[Boolean], <<?[Boolean], <<?[java.sql.Timestamp], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table tasks. Objects of this class serve as prototypes for rows in queries. */
  class Tasks(_tableTag: Tag) extends profile.api.Table[TasksRow](_tableTag, "tasks") {
    def * = (id, title, description, notes, iscategory, isvisibletocustomer, duedate, assigneduserid, usercreatedid, businessid, projectid, parenttaskid, modifieddate, createddate) <> (TasksRow.tupled, TasksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), title, description, notes, iscategory, isvisibletocustomer, duedate, Rep.Some(assigneduserid), Rep.Some(usercreatedid), Rep.Some(businessid), Rep.Some(projectid), Rep.Some(parenttaskid), modifieddate, createddate).shaped.<>({r=>import r._; _1.map(_=> TasksRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8.get, _9.get, _10.get, _11.get, _12.get, _13, _14)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column title SqlType(varchar), Length(1024,true), Default(None) */
    val title: Rep[Option[String]] = column[Option[String]]("title", O.Length(1024,varying=true), O.Default(None))
    /** Database column description SqlType(varchar), Length(1024,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(1024,varying=true), O.Default(None))
    /** Database column notes SqlType(varchar), Length(1024,true), Default(None) */
    val notes: Rep[Option[String]] = column[Option[String]]("notes", O.Length(1024,varying=true), O.Default(None))
    /** Database column isCategory SqlType(bool), Default(None) */
    val iscategory: Rep[Option[Boolean]] = column[Option[Boolean]]("isCategory", O.Default(None))
    /** Database column isVisibleToCustomer SqlType(bool), Default(None) */
    val isvisibletocustomer: Rep[Option[Boolean]] = column[Option[Boolean]]("isVisibleToCustomer", O.Default(None))
    /** Database column dueDate SqlType(timestamp), Default(None) */
    val duedate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("dueDate", O.Default(None))
    /** Database column assignedUserId SqlType(serial), AutoInc */
    val assigneduserid: Rep[Int] = column[Int]("assignedUserId", O.AutoInc)
    /** Database column userCreatedID SqlType(serial), AutoInc */
    val usercreatedid: Rep[Int] = column[Int]("userCreatedID", O.AutoInc)
    /** Database column businessId SqlType(serial), AutoInc */
    val businessid: Rep[Int] = column[Int]("businessId", O.AutoInc)
    /** Database column projectId SqlType(serial), AutoInc */
    val projectid: Rep[Int] = column[Int]("projectId", O.AutoInc)
    /** Database column parentTaskId SqlType(serial), AutoInc */
    val parenttaskid: Rep[Int] = column[Int]("parentTaskId", O.AutoInc)
    /** Database column modifiedDate SqlType(timestamp), Default(None) */
    val modifieddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("modifiedDate", O.Default(None))
    /** Database column createdDate SqlType(timestamp), Default(None) */
    val createddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("createdDate", O.Default(None))

    /** Foreign key referencing Businesses (database name tasks_businessId_fkey) */
    lazy val businessesFk = foreignKey("tasks_businessId_fkey", businessid, Businesses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Projects (database name tasks_projectId_fkey) */
    lazy val projectsFk = foreignKey("tasks_projectId_fkey", projectid, Projects)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Users (database name tasks_assignedUserId_fkey) */
    lazy val usersFk3 = foreignKey("tasks_assignedUserId_fkey", assigneduserid, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Users (database name tasks_userCreatedID_fkey) */
    lazy val usersFk4 = foreignKey("tasks_userCreatedID_fkey", usercreatedid, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Tasks */
  lazy val Tasks = new TableQuery(tag => new Tasks(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(50,true), Default(None)
   *  @param password Database column password SqlType(varchar), Length(25,true), Default(None)
   *  @param email Database column email SqlType(varchar), Length(100,true), Default(None)
   *  @param businessid Database column businessId SqlType(serial), AutoInc
   *  @param isadmin Database column isAdmin SqlType(bool), Default(None)
   *  @param iscustomer Database column isCustomer SqlType(bool), Default(None)
   *  @param isanemployee Database column isAnEmployee SqlType(bool), Default(None)
   *  @param modifieddate Database column modifiedDate SqlType(timestamp), Default(None)
   *  @param createddate Database column createdDate SqlType(timestamp), Default(None) */
  final case class UsersRow(id: Int, username: Option[String] = None, password: Option[String] = None, email: Option[String] = None, businessid: Int, isadmin: Option[Boolean] = None, iscustomer: Option[Boolean] = None, isanemployee: Option[Boolean] = None, modifieddate: Option[java.sql.Timestamp] = None, createddate: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Boolean]], e3: GR[Option[java.sql.Timestamp]]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<[Int], <<?[Boolean], <<?[Boolean], <<?[Boolean], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, username, password, email, businessid, isadmin, iscustomer, isanemployee, modifieddate, createddate) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), username, password, email, Rep.Some(businessid), isadmin, iscustomer, isanemployee, modifieddate, createddate).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2, _3, _4, _5.get, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(50,true), Default(None) */
    val username: Rep[Option[String]] = column[Option[String]]("username", O.Length(50,varying=true), O.Default(None))
    /** Database column password SqlType(varchar), Length(25,true), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Length(25,varying=true), O.Default(None))
    /** Database column email SqlType(varchar), Length(100,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(100,varying=true), O.Default(None))
    /** Database column businessId SqlType(serial), AutoInc */
    val businessid: Rep[Int] = column[Int]("businessId", O.AutoInc)
    /** Database column isAdmin SqlType(bool), Default(None) */
    val isadmin: Rep[Option[Boolean]] = column[Option[Boolean]]("isAdmin", O.Default(None))
    /** Database column isCustomer SqlType(bool), Default(None) */
    val iscustomer: Rep[Option[Boolean]] = column[Option[Boolean]]("isCustomer", O.Default(None))
    /** Database column isAnEmployee SqlType(bool), Default(None) */
    val isanemployee: Rep[Option[Boolean]] = column[Option[Boolean]]("isAnEmployee", O.Default(None))
    /** Database column modifiedDate SqlType(timestamp), Default(None) */
    val modifieddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("modifiedDate", O.Default(None))
    /** Database column createdDate SqlType(timestamp), Default(None) */
    val createddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("createdDate", O.Default(None))

    /** Foreign key referencing Businesses (database name users_businessId_fkey) */
    lazy val businessesFk = foreignKey("users_businessId_fkey", businessid, Businesses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
