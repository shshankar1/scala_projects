package com.example.section4

import org.apache.spark.{SparkConf, SparkContext}


object MostPopularSuperhero {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("MostPopularSuperhero").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    val names = sparkContext.textFile("data/Marvel-names.txt")
    val namesRDD = names.flatMap(parseSuperheroNames)

    val graph = sparkContext.textFile("data/Marvel-graph.txt")
    val graphRDD = graph.map(countCoOccurance)
    val totalFriendsByCharacter = graphRDD.reduceByKey(_ + _)
    val mostPopular = totalFriendsByCharacter.map(character => (character._2, character._1)).max()
    val mostPopularSuperhero = namesRDD.lookup(mostPopular._2)
    println(mostPopularSuperhero(0), "with co occurrence:", mostPopular._1)

  }

  def parseSuperheroNames(line: String): Option[(Int, String)] = {
    val data = line.split("\"")
    if (data.length > 1) {
      Some(data(0).trim.toInt, data(1))
    } else {
      None
    }
  }

  def countCoOccurance(line: String): (Int, Int) = {
    val data = line.split("\\s+")
    (data(0).trim.toInt, data.length - 1)
  }

}
