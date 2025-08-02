import scala.collection.immutable.Stack
//Create a linked list class and add a reverse method.
//find number of occurence of numbers in a list
//merge intervals
//LRU Cache

// Merge Intervals
val intervals = Seq((8, 10), (9, 12), (13, 14), (14, 15), (17, 19), (16, 20), (22, 24))

val mergedIntervals = new scala.collection.mutable.Stack[(Int, Int)]()

mergedIntervals.push(intervals.head)

def mergeInterval(currentInterval: (Int, Int)):Unit = {
  val currStartTime = currentInterval._1
  val currEndTime = currentInterval._2

  val lastInterval = mergedIntervals.pop
  val lastStartTime = lastInterval._1
  val lastEndTime = lastInterval._2

  if (lastEndTime >= currStartTime) {

    if (lastStartTime <= lastEndTime) {
      mergedIntervals.push((lastStartTime, currEndTime))

    } else if (lastStartTime > currStartTime && currEndTime > lastEndTime) {
      mergedIntervals.push((currStartTime, currEndTime))

    } else {
      mergedIntervals.push((currStartTime, lastEndTime))
    }

  } else {
    mergedIntervals.push(lastInterval)
    mergedIntervals.push(currentInterval)
  }
}

intervals.tail.foreach(mergeInterval)
for (elem <- mergedIntervals) {println(elem)}


// Reverse method in a linked list
