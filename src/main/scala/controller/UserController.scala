package controller

import akka.http.scaladsl.server.{Directives, Route}
import dto.{User, UserEntity}
import io.circe.{Decoder, Encoder, Json}
import org.joda.time.DateTime
import repository.UserRepository
import utils._


class UserController(userRepository: UserRepository) extends Controller
  with Directives with ActionHandlers with ValidatorHandlers with DateConversion {

  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  override def routes: Route = pathPrefix("api" / "v1.0" / "user") {
    pathEndOrSingleSlash {
      post {
        entity(as[UserEntity]) { entity =>
          validateWith(UserValidator)(entity) {
            handleOption(userRepository.save(User(entity)))(ApiSuccess.created) { success =>
              complete(success.statusCode, success.data)
            }
          }
        }
      }
    } ~ parameter("id".as[Int]) { id => {
      concat(
        get {
          handle(userRepository.get(id))(ApiSuccess.ok)(success => complete(success.statusCode, success.data))
        },
        put {
          entity(as[UserEntity]) { entity =>
            validateWith(UserValidator)(entity) {
              handle(userRepository.update(User(id, entity)))(ApiSuccess.ok)(success => complete(success.statusCode, success.data))
            }
          }
        },
        delete {
          handle(userRepository.delete(id))(ApiSuccess.ok)(success => complete(success.statusCode, success.data))
        }
      )
    }
    } ~ path("list") {
      get {
        parameters("limit".as[Int] ? 10, "offset".as[Int] ? 0, "sortBy" ? "lastName") { // should use pagination
          (limit, offset, sortBy) =>
            handle(userRepository.list(limit, offset, sortBy))(ApiSuccess.ok)(success => complete(success.statusCode, success.data))
        }
      }
    }
  }
}
