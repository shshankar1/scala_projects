package series1.module3.example
/*
Scala provides below scope options:
1. object-private (only current instance has access, rest instance don't)
2. private (available within same class)
3. protected (available within same or subclasses)
4. package
5. package-specific
6. public
 */
object AccessSpecifierExample {
  def main(args: Array[String]): Unit = {
    val shape = new Circle;
    shape.callDrawFunction()
  }
}

//object-private example
class Foo {
  private[this] def isFoo = true

  def doFoo(other: Foo): Unit = {
    //commented out line has compilation issue because isFoo is
    //accessible to only current instance not other
    /*if(other.isFoo){

    }*/
  }
}

//private example
class Animal {
  private def heartBeat: Unit = {
    println("heart beating....")
  }
}

class Dog extends Animal {
  //below method call is not possible because it is private
  //heartBeat
}

//protected example
class Shape {
  protected def draw():Unit={
    print("drawing...")
  }
}

class Circle extends Shape{
  override protected def draw(): Unit = {
    super.draw()
    print(" circle\n")
  }

  def callDrawFunction():Unit={
    draw()
  }
}