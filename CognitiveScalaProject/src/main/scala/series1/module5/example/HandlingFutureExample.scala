package series1.module5.example

import java.util.concurrent.ForkJoinPool

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object HandlingFutureExample {
  def main(args: Array[String]): Unit = {
    implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(new ForkJoinPool())
    implicit val timeout = 3 second

    val f:Future[Unit] = Future{
      Thread.sleep(2000l)
      println("hello")
      //throw new Exception()
    }(ec)

    Await.result(f,timeout)

    f.onComplete{
      case Success(())=>println("completed")
      //case Failure(())=>println("failure")
    }
  }
}
