package module1.example

import org.apache.spark.{SparkConf, SparkContext}
import util.Files


object WordCountExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Cognitive Classes").setMaster("local")
    val sparkContext = new SparkContext("local", "Word Count", conf)

    val input = sparkContext.textFile("data/shakespeare/all-shakespeare.txt")

    val wc = input.map(_.toLowerCase)
                .flatMap(text => text.split("""\W+"""))
                .groupBy(word => word)
                .mapValues(group => group.size)

    val output = "output/word-count"
    Files.rmrf(output)
    wc.saveAsTextFile(output)
    sparkContext.stop()
  }
}
