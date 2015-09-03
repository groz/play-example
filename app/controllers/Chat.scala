package controllers

import akka.actor._

class Chat extends Actor {

  override def receive: Actor.Receive = process(Set.empty)

  def process(subscribers: Set[ActorRef]): Receive = {

    case Join =>
      context become process(subscribers + sender)

    case ClientMessage(text) =>
      (subscribers - sender).foreach { _ ! ClientMessage(text) }

  }
}
