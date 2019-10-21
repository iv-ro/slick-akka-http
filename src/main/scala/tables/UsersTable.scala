package tables

import application.Db
import dto.User
import org.joda.time.DateTime


trait UsersTable {
  this: Db =>

  import config.profile.api._

  implicit val datetime2date: BaseColumnType[DateTime] = MappedColumnType.base[DateTime, java.sql.Date](
    d => new java.sql.Date(d.getMillis),
    d => DateTime.parse(d.toString))


  class Users(tag: Tag) extends Table[User](tag, "USERS") {

    def id = column[Long]("USER_ID", O.PrimaryKey, O.AutoInc)

    def firstName = column[String]("USER_FIRST_NAME", O.Length(64))

    def lastName = column[String]("USER_LAST_NAME", O.Length(64))

    def birthDate = column[DateTime]("USER_BIRTH_DATE")

    def address = column[String]("USER_ADDRESS", O.Length(256))

    def * = (id.?, firstName, lastName, birthDate, address) <> (User.tupled, User.unapply)

  }

  val users = TableQuery[Users]

}

