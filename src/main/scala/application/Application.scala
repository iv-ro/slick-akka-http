package application

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives.{reject, _}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Flow
import controller.{Controller, UserController}
import repository.UserRepository

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends App with DbConfiguration {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val userRepository: UserRepository = new UserRepository(db)
  userRepository.init()
  val userController: Controller = new UserController(userRepository)


  lazy val routes: Route = Seq(userController).foldRight[Route](reject) {
    (partial, builder) =>
      partial.routes ~ builder
  }
  lazy val router: Flow[HttpRequest, HttpResponse, NotUsed] =
    Route.handlerFlow(routes)

  val server: Server = new Server(router, "localhost", 8888)

  val binding = server.bind()


}

