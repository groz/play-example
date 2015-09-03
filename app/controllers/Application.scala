package controllers

import javax.inject._
import akka.actor.Actor.Receive
import akka.actor._
import play.api._
import play.api.mvc._
import play.api.Play.current

@Singleton
class Application @Inject()(actorSystem: ActorSystem) extends Controller {

  val chat = actorSystem.actorOf(Props[Chat])

  def socket = WebSocket.acceptWithActor[String, String] { request => out => Props(new ClientActor(out, chat))}

  def index = Action {
    Ok(views.html.index("Hello."))
  }

}
