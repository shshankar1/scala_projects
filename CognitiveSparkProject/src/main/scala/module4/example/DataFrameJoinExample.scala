package module4.example

import module3.example.data.{Airport, Flight}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object DataFrameJoinExample {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("DataFrameJoinExample").setMaster("local")
    sparkConf.set("spark.sql.shuffle.partitions", "4")
    sparkConf.set("spark.app.id", "DataFrameJoinExample")

    val sparkContext = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sparkContext)

    try {
      val airportsPath = "data/airline-flights/airports.csv"
      val flightsPath = "data/airline-flights/alaska-airlines/2008.csv"

      val airportsRDD = for {
        line <- sparkContext.textFile(airportsPath)
        airport <- Airport.parse(line)
      } yield airport

      val flightsRDD = for {
        line <- sparkContext.textFile(flightsPath)
        flight <- Flight.parse(line)
      } yield flight

      val airports = sqlContext.createDataFrame(airportsRDD)
      val flights = sqlContext.createDataFrame(flightsRDD)

      airports.cache
      flights.cache

      println("Airport schema: ")
      airports.printSchema

      println("Flights schema: ")
      flights.printSchema

      println("Airports, first 10 records:")
      airports.show(10)

      println("Flights, first 10 records:")
      flights.show(10)

      import sqlContext.implicits._

      val flights_between_airports = flights.select($"origin", $"dest")
        .groupBy($"origin", $"dest").count
        .orderBy($"count".desc, $"origin", $"dest")


      println("Flights between airports, sorted by airports: ")
      flights_between_airports.show

      flights_between_airports.cache

      val fba = flights_between_airports
      val air = airports

      val fba2 = fba.join(air, air("iata") === fba("origin"))
        .select($"origin", $"airport", $"dest", $"count")
        .toDF("origin", "origin_airport", "dest", "count")
        .join(air, air("iata") === $"dest")
        .select($"origin", $"origin_airport", $"dest", $"airport", $"count")
        .toDF("origin", "origin_airport", "dest", "dest_airport", "count")

      println("fba2 schema:")
      fba2.printSchema

      println("fba2, first 10 records:")
      fba2.show(10)

    } finally {
      sparkContext.stop()
    }

  }
}
