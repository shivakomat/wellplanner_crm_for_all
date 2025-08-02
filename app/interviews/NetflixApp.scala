package interviews

import scala.io.BufferedSource

object NetflixApp extends App {

  import org.joda.time.{DateTime, DateTimeZone}

  import scala.io.Source
  import scala.util.Try

  println(DateTime.now(DateTimeZone.UTC).getMillis)

  case class TvShow(id: Int, showType: String, title: String, director: String, cast: String,
                    country: String, date_added: String, release_year: String, rating: String,
                    duration: Double, listed_in: String, description: String)

//  val file = Source.fromFile("/Users/shivakomatreddy/Desktop/Coding Problems/TestData/netflix_titles.csv")
//  val netflixShowsStream = file.getLines()

  def getDuration(data: String): Double = {
    Try(data.split(" ").head.toDouble).getOrElse(0)
  }

  def showIdTrim(data: String): Int = {
    if(data != "") {
      Try(data.toInt).getOrElse({
        Try(data.replaceAll("[^0-9]", "").toInt).getOrElse(0)
      })
    } else 0
  }

  var rowCount = 1

//  val dataFrame = netflixShowsStream.drop(1).map(row => {
//    rowCount = rowCount + 1
//    val rowSeq = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").toSeq
//    if(rowSeq.size == 12) {
//      val id = showIdTrim(rowSeq.head)
//      TvShow(id = id, showType = rowSeq(1), title = rowSeq(2),
//        director = rowSeq(3), cast = rowSeq(4), country = rowSeq(5),
//        date_added = rowSeq(6), release_year = rowSeq(7), rating = rowSeq(8),
//        duration = getDuration(rowSeq(9)), rowSeq(10), rowSeq(11))
//    }
//  })

  println(DateTime.now(DateTimeZone.UTC).getMillis)


  // get data file size
  // split data file

  val bigFile: BufferedSource = Source.fromFile("/Users/shivakomatreddy/Desktop/Coding Problems/TestData/netflix_titles.csv")
  val numberOfChunks = 10
  println("Start of process getting file length")
  val chunkSize =  1298189580 / numberOfChunks


  (1 to numberOfChunks).foreach { chunkNumber =>
    println("Start of Chunk " + chunkNumber)
    val dataSet: Iterator[String] = bigFile.getLines()
    val rowsInAChunk: Seq[String] = dataSet.toSeq
    println("Start of Chunk " + chunkNumber + " loaded in memory")
    val listOfTvShows = rowsInAChunk.map(row => {
      val rowSeq = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").toSeq
      if(rowSeq.size == 12) {
        val id = showIdTrim(rowSeq.head)
        Some(TvShow(id = id, showType = rowSeq(1), title = rowSeq(2),
          director = rowSeq(3), cast = rowSeq(4), country = rowSeq(5),
          date_added = rowSeq(6), release_year = rowSeq(7), rating = rowSeq(8),
          duration = getDuration(rowSeq(9)), rowSeq(10), rowSeq(11)))
      } else None
    })
    println("Start of Chunk " + chunkNumber + " started sorting on the chunk")
    println(listOfTvShows.size)
    listOfTvShows.flatten.foreach(tvShow => println((tvShow.title + "->" + tvShow.rating)))
//    val sortedByIdTvShows = listOfTvShows.flatten.sortBy(_.id)
//    sortedByIdTvShows.foreach(tvShow => println((tvShow.title + "->" + tvShow.rating)))
    println("End of Chunk " + chunkNumber)
  }

  println(DateTime.now(DateTimeZone.UTC).getMillis)
}
