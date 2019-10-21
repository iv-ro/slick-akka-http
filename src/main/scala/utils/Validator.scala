package utils

trait Validator[T] {
  def validate(t: T): Option[ApiError]
}
