package series1.module2.example

object ImmutableExample {
  def main(args: Array[String]): Unit = {
    val immutable = new Immutable("true")

    val immutableWithPublicProperty = new ImmutableWithPublicProperty("public")
    println(immutableWithPublicProperty.name)

    val mutable = new Mutable("Shashi")
    mutable.name = "Shankar"
    println(mutable.name)
  }
}

//class has single property which is private
class Immutable(name: String) {
  println(name)
}

//class has single property which is public and accessible from outside
class ImmutableWithPublicProperty(val name: String) {
  println(name)
}

//class has single property which is mutable and accessible from outside
class Mutable(var name: String) {
  println(name)
}
