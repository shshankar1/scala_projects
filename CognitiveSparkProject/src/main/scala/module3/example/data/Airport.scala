package module3.example.data

case class Airport(
                    iata: String, // 1: IATA code
                    airport: String, // 2: Name
                    city: String, // 3: City
                    state: String, // 4: State
                    country: String, // 5: Country
                    latitude: Float, // 6: Latitude
                    longitude: Float // 7: Longitude
                  )

object Airport {
  // All the string fields are quoted, including in the header row, UNLESS
  // the value is NA. There is still one entry we don't handle correctly,
  // where the name is "W. H. \"Bud\" Barron ", due to the escaped quotes.
  //import data.Conversions.toInt

  val headerRE = """^\s*"iata"\s*,.*""".r
  val lineRE = """^\s*"([^"]+)"\s*,\s*"([^"]+)"\s*,\s*"?([^"]+)"?\s*,\s*"?([^"]+)"?\s*,\s*"([^"]+)"\s*,\s*([-.\d]+)\s*,\s*([-.\d]+)\s*$""".r

  def parse(s: String): Option[Airport] = s match {
    case headerRE() => None
    case lineRE(iata, airport, city, state, country, lat, lng) =>
      Some(Airport(iata.trim, airport.trim, city.trim, state.trim, country.trim, lat.toFloat, lng.toFloat))
    case line =>
      Console.err.println(s"ERROR: Invalid Airport line: $line")
      None
  }
}
