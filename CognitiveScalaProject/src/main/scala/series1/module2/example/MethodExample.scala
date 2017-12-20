package series1.module2.example

object MethodExample {
  def main(args:Array[String]):Unit={
    val hollow = new Hollow
    hollow.echo("Shashi")
  }
}

class Hollow{
  def echo(name: String):Unit=println("Echo "+name)
}
