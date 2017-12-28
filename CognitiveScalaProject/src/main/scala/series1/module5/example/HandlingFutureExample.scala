package series1.module5.example

import java.util.concurrent.ForkJoinPool

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object HandlingFutureExample {
  def main(args: Array[String]): Unit = {
    implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(new ForkJoinPool())
    implicit val timeout = 1 second

    val f:Future[Unit] = Future{
      Thread.sleep(2000l)
      println("hello")
    }

    ec.

    f.onComplete{
      case Success(())=>println("completed")
      //case Failure(())=>println("failure")
    }
  }
}
