package dto


import org.joda.time.DateTime

case class User(
                 id: Option[Long],
                 firstName: String,
                 lastName: String,
                 birthDate: DateTime,
                 address: String //probably this relation should be done via model ref
               )

object User {
  def apply(createUser: UserEntity): User = new User(
    id = None,
    firstName = createUser.firstName,
    lastName = createUser.lastName,
    birthDate = createUser.birthDate,
    address = createUser.address
  )

  def apply(id: Long, updateUser: UserEntity): User = new User(
    Some(id),
    firstName = updateUser.firstName,
    lastName = updateUser.lastName,
    birthDate = updateUser.birthDate,
    address = updateUser.address,
  )

  val tupled: ((Option[Long], String, String, DateTime, String)) => User = (User.apply(_: Option[Long], _: String, _: String, _: DateTime, _: String)).tupled

}