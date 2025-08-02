import org.joda.time.{DateTime, DateTimeZone}

import scala.io.Source
import scala.util.Try


println(DateTime.now(DateTimeZone.UTC).getMillis)

case class TvShow(id: Int, showType: String, title: String, director: String, cast: String,
                  country: String, date_added: String, release_year: String, rating: String,
                  duration: Double, listed_in: String, description: String)

val file = Source.fromFile("/Users/shivakomatreddy/Desktop/Coding Problems/TestData/netflix_titles.csv")
val netflixShowsStream = file.getLines()

def getDuration(data: String): Double = {
  Try(data.split(" ").head.toDouble).getOrElse(0)
}


val dataFrame = netflixShowsStream.drop(1).map(row => {
  val rowSeq = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").toSeq
  if(rowSeq.head.toInt != 80119194) {
    TvShow(id = rowSeq.head.toInt, showType = rowSeq(1), title = rowSeq(2),
      director = rowSeq(3), cast = rowSeq(4), country = rowSeq(5),
      date_added = rowSeq(6), release_year = rowSeq(7), rating = rowSeq(8),
      duration = getDuration(rowSeq(9)), rowSeq(10), rowSeq(11))
  }
})

println(DateTime.now(DateTimeZone.UTC).getMillis)

dataFrame.foreach(_ => println(""))


println(DateTime.now(DateTimeZone.UTC).getMillis)

