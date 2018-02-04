package com.example.section4

import java.nio.charset.CodingErrorAction

import org.apache.spark.{SparkConf, SparkContext}

import scala.io.{Codec, Source}

object PopularMovieNameByNumberOfUserViews {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("PopularMovieNameByNumberOfUserViews").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)
    val moviesDict = sparkContext.broadcast(loadMoviesData)
    val movieViewCount = sparkContext.textFile("data/ml-10M100K/ratings.dat")
                                      .map(line => (line.split("::")(1).toInt, 1))
                                      .reduceByKey(_ + _)
    val movieViewSortByCount = movieViewCount.map(count => (count._2, count._1)).sortByKey()
    val movieNamesSortedByCount = movieViewSortByCount.map(count => (moviesDict.value(count._2), count._1))
    movieNamesSortedByCount.collect().foreach(println)
  }

  def loadMoviesData(): Map[Int, String] = {
    // Handle character encoding issues:
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

    val movies = Source.fromFile("data/ml-10M100K/movies.dat").getLines().map(data => {
      val movieInfo = data.split("::")
      (movieInfo(0).toInt, movieInfo(1))
    }).toMap
    movies
  }

}
