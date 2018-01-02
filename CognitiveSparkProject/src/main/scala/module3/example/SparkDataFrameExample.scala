package module3.example

import module3.example.data.{Airport, Flight}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import util.Printer
;

object SparkDataFrameExample {
  var out = Console.out // Overload for tests
  var quiet = false

  def main(args: Array[String]): Unit = {
    val sparkConfig = new SparkConf().setAppName("Spark Data Frames").setMaster("local")
    sparkConfig.set("spark.sql.shuffle.partitions", "4")
    sparkConfig.set("spark.app.id", "SparkDataFrames")

    val sparkContext = new SparkContext(sparkConfig)
    val sqlContext = new SQLContext(sparkContext)

    import sqlContext.implicits._

    val flightsFile = "data/airline-flights/alaska-airlines/2008.csv"
    val airportsFile = "data/airline-flights/airports.csv"

    val flightsRDD = for {
      line <- sparkContext.textFile(flightsFile)
      flight <- Flight.parse(line)
    } yield flight

    val airportRDD = for {
      line <- sparkContext.textFile(airportsFile)
      airport <- Airport.parse(line)
    } yield airport

    val flights = sqlContext.createDataFrame(flightsRDD)
    val airports = sqlContext.createDataFrame(airportRDD)

    flights.cache
    airports.cache

    // SELECT COUNT(*) FROM flights f WHERE f.canceled > 0;
    val canceled_flights = flights.filter(flights("canceled") > 0)
    Printer(out, "canceled flights", canceled_flights)
    if (!quiet) {
      out.println("\ncanceled_flights.explain(extended = false):")
      canceled_flights.explain(extended = false)
      out.println("\ncanceled_flights.explain(extended = true):")
      canceled_flights.explain(extended = true)
    }
    canceled_flights.cache

    // Note how we can reference the columns several ways:
    if (!quiet) {
      flights.orderBy(flights("origin")).show
      flights.orderBy("origin").show
      flights.orderBy($"origin").show
      flights.orderBy($"origin".desc).show
    }

    // SELECT cf.date.month AS month, COUNT(*)
    //   FROM canceled_flights cf
    //   GROUP BY cf.date.month
    //   ORDER BY month;
    val canceled_flights_by_month = canceled_flights.
      groupBy("date.month").count()
    Printer(out, "canceled flights by month", canceled_flights_by_month)
    if (!quiet) {
      out.println("\ncanceled_flights_by_month.explain(true):")
      canceled_flights_by_month.explain(true)
    }
    canceled_flights.unpersist

    // Before running the next query, change the shuffle.partitions property to 50:
    sqlContext.setConf("spark.sql.shuffle.partitions", "50")

    // We used "50" instead of "4". Run the query. How much time does it take?
    //
    // SELECT origin, dest, COUNT(*) AS cnt
    //   FROM flights
    //   GROUP BY origin, dest
    //   ORDER BY cnt DESC, origin, dest;
    /*val flights_between_airports50 = flights.select($"origin", $"dest").
      groupBy($"origin", $"dest").count().
      orderBy($"count".desc, $"origin", $"dest")
    Printer(out, "Flights between airports, sorted by airports", flights_between_airports50)*/

    // Now change it back, run the query and compare the time. Does the output change?
    sqlContext.setConf("spark.sql.shuffle.partitions", "4")
    val flights_between_airports = flights.select($"origin", $"dest").
      groupBy($"origin", $"dest").count().
      orderBy($"count".desc, $"origin", $"dest")
    Printer(out, "Flights between airports, sorted by airports", flights_between_airports)
    if (!quiet) {
      println("\nflights_between_airports.explain(true):")
      flights_between_airports.explain(true)
    }

    flights_between_airports.cache



  }
}
