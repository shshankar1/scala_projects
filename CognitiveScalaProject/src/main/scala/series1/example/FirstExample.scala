package series1.example

import org.apache.spark.ml.feature.NGram
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object FirstExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Cognitive Classes").setMaster("local")
    val sparkContext = new SparkContext(conf)
    val sqlContext = new SQLContext(sparkContext)

    val wordDataFrame = sqlContext.createDataFrame(Seq(
      (0, Array("Hi", "I", "want", "to", "learn", "Data", "Science")),
      (1, Array("I", "want", "to", "use", "Scala")),
      (2, Array("This", "is", "easy", "and", "fun"))
    )).toDF("labels", "words")

    val ngram = new NGram().setInputCol("words").setOutputCol("ngrams")

    val ngramDataFrame = ngram.transform(wordDataFrame)

    ngramDataFrame.take(3).map(line => line.getAs[Stream[String]]("ngrams").toList).foreach(println)

  }
}
