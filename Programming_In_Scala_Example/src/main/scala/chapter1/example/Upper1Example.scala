package chapter1.example

object Upper1Example {
  def main(args: Array[String]): Unit = {
    val upper1 = new Upper1
    println(upper1.upper("shashi", "shankar"))

    val upper2 = new Upper2
    println(upper2.upper("lalsa","kumari"))
  }
}

case class Upper1() {
  def upper(strings: String*): Seq[String] = {
    strings.map(_.toUpperCase)
  }
}

case class Upper2(){
  def upper(strings:String*):Seq[String]={
    strings.map(s=>s.toUpperCase)
  }
}
