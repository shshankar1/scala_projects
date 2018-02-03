package com.example.section4

import org.apache.spark.{SparkConf, SparkContext}

object PopularMoviesByNumberOfUserViews {
  def main(agrs: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("PopularMoviesByNumberOfUserViews").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)
    val movies = sparkContext.textFile("data/ml-10M100K/ratings.dat")
    val moviesViewCount = movies.map(movie => (movie.split("::")(1), 1)).reduceByKey(_+_)
    val sortedMoviesViewCount = moviesViewCount.map(viewCount => (viewCount._2, viewCount._1)).sortByKey()
    sortedMoviesViewCount.collect().foreach(viewCount => println(viewCount._2, viewCount._1))
  }
}
