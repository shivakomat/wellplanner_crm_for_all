package interviews

import interviews.NetflixApp.{TvShow, getDuration, showIdTrim}
import org.joda.time.{DateTime, DateTimeZone}

object TestApp extends App {


  import scala.io.Source

  println(DateTime.now(DateTimeZone.UTC).getMillis)


  val file = Source.fromFile("/Users/shivakomatreddy/Desktop/Coding Problems/TestData/netflix_titles.csv")

  val fileStream = file.getLines()

  val header = if(fileStream.hasNext) fileStream.next() else ""

  processInChunks(fileStream)

  println(DateTime.now(DateTimeZone.UTC).getMillis)


  def processInChunks(fileStream: Iterator[String]): Unit = {
    (1 to 7).foreach { round =>

      println(s"Round ${round} Started")
      println("-----------------------------------------")
      println(DateTime.now(DateTimeZone.UTC).getMillis)
      val df = getNextChunk(250000, fileStream)
      df.foreach(_ => print(""))
      df.sortBy(_.id)
        .foreach(_ => print(""))
      println(s"Round ${round} Completed")
      println("-----------------------------------------")
      println(DateTime.now(DateTimeZone.UTC).getMillis)
    }
  }

  def sortDataById(tvShowData: Seq[TvShow]): Seq[TvShow] = {
    tvShowData.sortBy(_.id)
  }

  def getNextChunk(chunkSize: Int, fileStream: Iterator[String]): Seq[TvShow] = {
    (1 to chunkSize).flatMap(rowNumber => {
      if(fileStream.hasNext) {
        convertToTvShowInstance(fileStream.next())
      } else {
        None
      }
    })
  }

  def convertToTvShowInstance(row: String): Option[TvShow] = {
    val rowData = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").toSeq
    rowData.size match {
      case 12 =>
        val id = showIdTrim(rowData.head)
        Some(TvShow(id = id, showType = rowData(1), title = rowData(2),
          director = rowData(3), cast = rowData(4), country = rowData(5),
          date_added = rowData(6), release_year = rowData(7), rating = rowData(8),
          duration = getDuration(rowData(9)), rowData(10), rowData(11)))
      case _ => None
    }
  }

}
