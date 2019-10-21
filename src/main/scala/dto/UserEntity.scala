package dto

import org.joda.time.DateTime

case class UserEntity(
                       firstName: String,
                       lastName: String,
                       birthDate: DateTime,
                       address: String)
