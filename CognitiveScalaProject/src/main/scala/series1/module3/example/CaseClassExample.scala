package series1.module3.example

object CaseClassExample {
  def main(args:Array[String]):Unit={
    val time = Time(3,24)
    println(time)

    val anotherTime = Time.apply(16,2)
    println(anotherTime.hours)
    //anotherTime.hours=20
    println(anotherTime.hours)

    //decontruct the object using unapply function which gives you a tuple of elements
    val deconstrTime = Time.unapply(anotherTime)
    println(deconstrTime.get._1)

    //comparing 2 objects of case class
    val t1 = new Time(3,4)
    val t2 = Time(3,4)
    println(t1==t2)

    //case object example
    println(Time.toString)
  }
}

case class Time(hours:Int, minutes:Int)

case object Time
