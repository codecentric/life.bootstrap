package de.codecentric.life

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.server.{Directives, RouteResult, _}
import akka.pattern.ask
import akka.stream.{ActorMaterializer, Materializer}
import akka.util.Timeout

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.language.postfixOps

object GameOfLifeActor {
  var routes: Route = _

  def main(args: Array[String]) {
    implicit val system = ActorSystem("GameOfLife")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher


    val gatewayProps: Props = Props[GameOfLifeActor]
    implicit lazy val mainActor = system.actorOf(gatewayProps)

    initRoutes()

    Http().bindAndHandle(RouteResult.route2HandlerFlow(routes), "0.0.0.0", 8080)
    println(s"Server online at http://localhost:8080/")

  }

  def initRoutes()(implicit gameOfLife: ActorRef, ec: ExecutionContext, mat: Materializer) = {
    import Directives._

    import scala.concurrent.duration._
    val homeRoute = pathSingleSlash {
      get {
        implicit val timeout = Timeout(5 seconds)
        val eventualGameOutput: Future[GameOutput] = (gameOfLife ? Tick).mapTo[GameOutput]

        complete(HttpResponse(200).withHeaders(new RawHeader("Refresh", "1")).withEntity(Await.result(eventualGameOutput.map(_.output),.5.seconds)))
      }
    }

    routes = homeRoute

  }
}

class GameOfLifeActor extends Actor {
  override def receive: Receive = {
    case Tick => sender() ! GameOutput(GameOfLife.tick())
  }
}

trait GameOfLifeMessage

case object Tick extends GameOfLifeMessage

case class GameOutput(output: String) extends GameOfLifeMessage