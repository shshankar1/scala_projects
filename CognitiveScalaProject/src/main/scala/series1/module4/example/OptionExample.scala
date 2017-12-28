package series1.module4.example

object OptionExample {
  def main(args:Array[String]):Unit={
    val some = Option("Roti")
    println(some.get)

    val none = Option(null)
    println(none.getOrElse("Rice"))

    val comp = Composition(firstName="enthyl",lastName="chloride")
    println(comp)
  }
}

case class Composition(firstName:String,middleName:Option[String]=None,lastName:String)
