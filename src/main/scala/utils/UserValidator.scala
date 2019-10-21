package utils

import dto.UserEntity
import org.joda.time.DateTime

object UserValidator extends Validator[UserEntity] {
  override def validate(createUser: UserEntity): Option[ApiError] = {
    if (createUser.firstName.isEmpty && createUser.lastName.isEmpty && createUser.address.isEmpty) {
      Some(ApiError.emptyUser)
    } else if (createUser.birthDate.isAfter(DateTime.now())) {
      Some(ApiError.futureDate)
    } else None
  }
}
