package module3.example.data

case class Flight(
                   date: Flight.Date, // fields 1-4.
                   times: Flight.Times, // fields 5-8, 12-16, 20-21
                   uniqueCarrier: String, //  9:  unique carrier code
                   flightNum: Int, // 10:  flight number
                   tailNum: String, // 11:  plane tail number
                   origin: String, // 17:  origin IATA airport code
                   dest: String, // 18:  destination IATA airport code
                   distance: Int, // 19:  in miles
                   canceled: Int, // 22:  was the flight canceled?
                   cancellationCode: String, // 23:  reason for cancellation (A = carrier, B = weather, C = NAS, D = security)
                   diverted: Int, // 24:  1 = yes, 0 = no
                   carrierDelay: Int, // 25:  in minutes
                   weatherDelay: Int, // 26:  in minutes
                   nasDelay: Int, // 27:  in minutes
                   securityDelay: Int, // 28:  in minutes
                   lateAircraftDelay: Int // 29:  in minutes
                 )

object Flight {

  /** Date type used in Flight instances. */
  case class Date(
                   year: Int, //  1:  1987-2008
                   month: Int, //  2:  1-12
                   dayOfMonth: Int, //  3:  1-31
                   dayOfWeek: Int //  4:  1 (Monday) - 7 (Sunday)
                 ) extends Ordered[Date] {
    def compare(that: Date): Int = {
      val diffYear = year - that.year
      if (diffYear != 0) diffYear
      else {
        val diffMonth = month - that.month
        if (diffMonth != 0) diffMonth
        else dayOfMonth - that.dayOfMonth
      }
    }

    // Counts from 1 and starts at Monday.
    private val dayOfWeekToString = Vector(
      "", "Monday", "Tuesday", "Wednesday", "Thurday", "Friday", "Saturday", "Sunday")

    override def toString =
      "%4d-%02d-%02d (%s)".format(year, month, dayOfMonth, dayOfWeekToString(dayOfWeek))
  }

  /** Times type used in Flight instances. */
  case class Times(
                    depTime: Int, //  5:  actual departure time (local, hhmm)
                    crsDepTime: Int, //  6:  scheduled departure time (local, hhmm)
                    arrTime: Int, //  7:  actual arrival time (local, hhmm)
                    crsArrTime: Int, //  8:  scheduled arrival time (local, hhmm)
                    actualElapsedTime: Int, // 12:  in minutes
                    crsElapsedTime: Int, // 13:  in minutes
                    airTime: Int, // 14:  in minutes
                    arrDelay: Int, // 15:  arrival delay, in minutes
                    depDelay: Int, // 16:  departure delay, in minutes
                    taxiIn: Int, // 20:  taxi in time, in minutes
                    taxiOut: Int // 21:  taxi out time in minutes
                  ) extends Ordered[Times] {
    /** Only compare the first four fields */
    def compare(that: Times): Int = {
      val diffDepTime = depTime - that.depTime
      if (diffDepTime != 0) diffDepTime
      else {
        val diffCRSDepTime = crsDepTime - that.crsDepTime
        if (diffCRSDepTime != 0) diffCRSDepTime
        else {
          val diffArrTime = arrTime - that.arrTime
          if (diffArrTime != 0) diffArrTime
          else crsArrTime - that.crsArrTime
        }
      }
    }

    // Write as a tuple.
    override def toString =
    s"($depTime,$crsDepTime,$arrTime,$crsArrTime,$actualElapsedTime,$crsElapsedTime,$airTime,$arrDelay,$depDelay,$taxiIn,$taxiOut)"
  }

  // The string fields are NOT quoted.
  def parse(line: String): Option[Flight] = {
    val fields = line.trim.split("""\s*,\s*""")
    try {
      if (fields(0) == "Year") None
      else {
        import data.Conversions.toInt
        Some(Flight(
          Date(
            toInt(fields(0)),
            toInt(fields(1)),
            toInt(fields(2)),
            toInt(fields(3))),
          Times(
            toInt(fields(4)),
            toInt(fields(5)),
            toInt(fields(6)),
            toInt(fields(7)),
            toInt(fields(11)),
            toInt(fields(12)),
            toInt(fields(13)),
            toInt(fields(14)),
            toInt(fields(15)),
            toInt(fields(19)),
            toInt(fields(20))),
          fields(8).trim,
          toInt(fields(9)),
          fields(10).trim,
          fields(16).trim,
          fields(17).trim,
          toInt(fields(18)),
          toInt(fields(21)),
          fields(22).trim,
          toInt(fields(23)),
          toInt(fields(24)),
          toInt(fields(25)),
          toInt(fields(26)),
          toInt(fields(27)),
          toInt(fields(28))))
      }
    } catch {
      case ex: NumberFormatException =>
        Console.err.println(s"$ex: line = $line")
        None
      case ex: IndexOutOfBoundsException =>
        Console.err.println(s"$ex: line = $line")
        None
    }
  }
}