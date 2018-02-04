package com.example.section4

import org.apache.spark.{SparkConf, SparkContext}

object TopTenPopularSuperhero {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("TopTenPopularSuperhero").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    val names = sparkContext.textFile("data/Marvel-names.txt")
    val namesRDD = names.flatMap(parseNames)

    val superheroGraph = sparkContext.textFile("data/Marvel-graph.txt")
    val superheroGraphRDD = superheroGraph.map(countCoOccurance)
    val topTenSuperheros = superheroGraphRDD.reduceByKey(_ + _).map(character => (character._2, character._1)).sortByKey().top(10)
    val topTenSuperherosNames = topTenSuperheros.map(superhero => (namesRDD.lookup(superhero._2), superhero._1))
    topTenSuperherosNames.foreach(println)
  }

  def parseNames(line: String): Option[(Int, String)] = {
    val data = line.split("\"")
    if (data.size > 1) {
      Option(data(0).trim.toInt, data(1))
    } else {
      None
    }
  }

  def countCoOccurance(line: String): (Int, Int) = {
    val data = line.split("\\s+")
    (data(0).toInt, data.length - 1)
  }
}
