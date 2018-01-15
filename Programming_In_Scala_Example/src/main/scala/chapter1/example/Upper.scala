package chapter1.example

object Upper {
  def main(args:Array[String]):Unit={
    args.map(_.toUpperCase).foreach(println(_))
  }
}
