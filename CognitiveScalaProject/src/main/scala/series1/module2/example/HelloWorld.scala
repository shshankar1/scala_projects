package series1.module2.example

class HelloWorld {
  println("Hello! World")
}

class HelloWorldWithParam(name: String) {
  println("Hello! " + name)
}

object HelloWorld {
  def main(args: Array[String]): Unit = {
    val obj = new HelloWorld()
    val newObj = new HelloWorldWithParam("Shashi")
  }
}
