package module2.example

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}
import util.{Files, StopWords}

import scala.util.matching.Regex

/**
  * Inverted Index - Basis of Search Engines.
  * Builds on the exercise solution that sorts by words and sorts the list of
  * (file_name, count) values by count descending. This version also filters
  * stop words, propagated as a Broadcast Variable.
  */
object InvertedIndexSortByWordsAndCounts {
  def main(args: Array[String]): Unit = {
    val inputPath = "output/crawl"
    val outputPath = "output/inverted-index"

    Files.rmrf(outputPath)

    val conf = new SparkConf().setAppName("Invereted Index Builder").setMaster("local")
    val sparkContext = new SparkContext(conf)
    val stopWords: Broadcast[Set[String]] = sparkContext.broadcast(StopWords.words)

    try {
      val lineRE = """^\s*\(([^,]+),(.*)\)\s*$""".r

      val input = sparkContext.textFile(inputPath).map {
        case lineRE(fileName, text) => (fileName.trim, text.toLowerCase)
        case badLine =>
          Console.err.print(s"Unexpected bad line: $badLine")
          ("", "")
      }

      println(s"Writing output to $outputPath")

      val numbersRE = """^\d+$""".r

      input.flatMap {
        case (path, text) =>
          text.trim.split("""[^\w']""").map(word => ((word, path), 1))
      }.filter {
        case ((word, _), _) => stopWords.value.contains(word) && numbersRE.findFirstIn(word) == None
      }.reduceByKey {
        (count1, count2) => count1 + count2
      }.map {
        case ((word, path), n) => (word, (path, n))
      }.groupByKey
        .sortByKey(ascending = true)
        .mapValues { iterable =>
          val path = iterable.to[Vector].sortBy {
            case (path, n) => (-n, path)
          }
          path.mkString(", ")
        }.saveAsTextFile(outputPath)
    } finally {
      println("Enter any key to exit:")
      Console.in.read()
    }
  }
}
