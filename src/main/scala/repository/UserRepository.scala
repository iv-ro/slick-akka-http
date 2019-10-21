package repository

import application.Db
import dto.User
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import tables.UsersTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

class UserRepository(val config: DatabaseConfig[JdbcProfile]) extends Repository[User] with Db with UsersTable {

  import config.profile.api._


  override def init(): Future[Unit] = {
    db.run(DBIO.seq(users.schema.create).asTry).map {
      case Success(_) => println(users.baseTableRow.tableName)
      case Failure(e) => println(s"Failed to create userTable, reason: $e")
    }
  }

  override def save(user: User): Future[Option[User]] = db
    .run((users returning users.map(_.id) += user).asTry)
    .map {
      case Success(id) => Some(user.copy(id = Some(id)))
      case Failure(e) => None
    }

  override def delete(id: Long): Future[Boolean] = db
    .run(users.filter(_.id === id).delete).map(_ > 0)

  override def update(updateVal: User): Future[Boolean] = {
    val query = for {
      user <- users if user.id === updateVal.id
    } yield (user.firstName, user.lastName, user.birthDate, user.address)

    db.run(query.update((updateVal.firstName, updateVal.lastName, updateVal.birthDate, updateVal.address)).map(_ > 0))
  }

  override def get(id: Long): Future[Option[User]] = db.run {
    (for {
      user <- users if user.id === id
    } yield user).result.headOption
  }

  override def list(limit: Int, offset: Int, sortBy: String): Future[(Seq[User], Int)] = {
    def sortFeature(users: Users) = sortBy match {
      case "address" => users.address
      case "firstName" => users.firstName
      case _ => users.lastName
    }

    val query = for {
      usersSeq <- db.run(users
        .sortBy(sortFeature)
        .drop(offset)
        .take(limit)
        .result)
      recordsTotal <- db.run(users.length.result)
    } yield (usersSeq, recordsTotal)
    query
  }
}
