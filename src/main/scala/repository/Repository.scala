package repository

import scala.concurrent.Future
import java.sql.Date

import akka.http.scaladsl.model.DateTime


trait Repository[T] {
  def init(): Future[Unit]

  def get(id: Long): Future[Option[T]]

  def save(saveVal: T): Future[Option[T]]

  def update(updateVal: T): Future[Boolean]

  def delete(id: Long): Future[Boolean]

  def list(limit: Int, offset: Int, sortBy: String): Future[(Seq[T], Int)]





}

