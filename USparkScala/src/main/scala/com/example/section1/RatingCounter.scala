package com.example.section1

import org.apache.spark.{SparkConf, SparkContext}

object RatingCounter {
  def main(args:Array[String]):Unit={
    val sparkConf = new SparkConf().setAppName("Ranking Counter").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    val data = sparkContext.textFile("data/ml-10M100K/ratings.dat")

    val counts = data.map(line=>(line.split("::")(2),1)).reduceByKey(_+_)
    counts.foreach(println)
  }
}