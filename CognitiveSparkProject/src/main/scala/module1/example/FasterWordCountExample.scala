package module1.example

import org.apache.spark.{SparkConf, SparkContext}
import util.Files

object FasterWordCountExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Cognitive Classes").setMaster("local")
    val sparkContext = new SparkContext("local", "Word Count", conf)

    val input = sparkContext.textFile("data/shakespeare/all-shakespeare.txt")

    val wc = input.map(_.toLowerCase)
      .flatMap(text => text.split("""\W+"""))
      .map(word => (word, 1))
      .reduceByKey((n1, n2) => n1 + n2)

    val output = "output/word-count"
    Files.rmrf(output)
    wc.saveAsTextFile(output)
    //uncomment the sleep to see the spark console to see the code performance
    //Thread.sleep(70000l)
    sparkContext.stop()
  }
}
