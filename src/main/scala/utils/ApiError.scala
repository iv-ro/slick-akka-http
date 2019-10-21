package utils

import akka.http.scaladsl.model.{StatusCode, StatusCodes}

final case class ApiError private(statusCode: StatusCode, data: String)

object ApiError {
  private def apply(statusCode: StatusCode, message: String): ApiError = new ApiError(statusCode, message)
  val generic: ApiError = new ApiError(StatusCodes.InternalServerError, "Unknown error.")
  val notFound: ApiError = new ApiError(StatusCodes.NotFound, "Not found.")
  val emptyUser: ApiError = new ApiError(StatusCodes.NotFound, "Fulfill user data")
  val futureDate: ApiError = new ApiError(StatusCodes.NotFound, "Impossible, user is from feature!")
}
