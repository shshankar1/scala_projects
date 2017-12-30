package module2.example

import org.apache.spark.{SparkConf, SparkContext}
import util.Files

object Crawl {
  def main(args: Array[String]): Unit = {
    val inputPath = "data/enron-spam-ham/*"
    val outputPath = "output/crawl"

    //delete any existing output directory
    Files.rmrf(outputPath)

    val separator = java.io.File.separator

    val conf = new SparkConf().setAppName("File Crawler").setMaster("local")

    val sparkContext = new SparkContext("local", "File Crawler", conf)

    try {
      val fileContent = sparkContext.wholeTextFiles(inputPath).map {
        case (id, text) =>
          val lastSep = id.lastIndexOf(separator)
          val id2 = if (lastSep < 0) id.trim else id.substring(lastSep + 1, id.length).trim
          val text2 = text.trim.replaceAll("""\s*\n\s*""", " ")
          (id2, text2)
      }
      //fileContent.collect().foreach(println)
      fileContent.saveAsTextFile(outputPath)
    }finally {
      //this is for checking code performance in Spark Web Console
      println("Enter any key to complete the execution:")
      Console.in.read()
      sparkContext.stop()
    }
  }

}
