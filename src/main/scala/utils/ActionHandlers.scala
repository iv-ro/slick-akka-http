package utils

import akka.http.scaladsl.server.{Directive1, Directives}

import scala.concurrent.Future
import scala.util.{Failure, Success}

trait ActionHandlers extends Directives {

  def handle[T](f: Future[T])(success: T => ApiSuccess[T]): Directive1[ApiSuccess[T]] = onComplete(f) flatMap {
    case Success(t) => provide(success(t))
    case Failure(e) =>
      complete(ApiError.generic.statusCode, e.getMessage)
  }

  def handleOption[T](f: Future[Option[T]])(success: T => ApiSuccess[T]): Directive1[ApiSuccess[T]] = onComplete(f) flatMap {
    case Success(tOpt) =>
      tOpt match {
        case Some(t) => provide(success(t))
        case None => complete(ApiError.generic.statusCode, ApiError.generic.data)
      }
    case Failure(e) => {
      println(e.getMessage)
      complete(ApiError.generic.statusCode)
    }
  }
}

