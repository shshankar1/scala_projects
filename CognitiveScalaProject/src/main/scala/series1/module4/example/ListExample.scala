package series1.module4.example

object ListExample {
  def main(args: Array[String]): Unit = {
    val names = List("Jack", "King", "Ryan", "Phillip")
    println(names)
    val updatedNames = "Shashi" +: names
    println(updatedNames)
  }
}
