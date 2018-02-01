package com.example.section3

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)
    val lines = sparkContext.textFile("data/book.txt")
    val words = lines.flatMap(line => line.split("\\W+"))
    val lowerCaseWords = words.map(word => word.toLowerCase)
    /*val countByWord = lowerCaseWords.countByValue()
    countByWord.foreach(elem=>println(elem))*/

    val wordCounts = lowerCaseWords.map(word => (word, 1)).reduceByKey((x, y) => x + y)
    //wordCounts.collect().foreach(println)

    val wordCountsSorted = wordCounts.map(word => (word._2, word._1)).sortByKey()
    wordCountsSorted.collect().foreach(word => println(word._2, word._1))

  }
}
