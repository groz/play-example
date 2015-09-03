package controllers

import akka.actor.{Actor, ActorRef}

class ClientActor(out: ActorRef, chat: ActorRef) extends Actor {

  chat ! Join

  override def receive: Receive = {
    case text: String =>
      chat ! ClientMessage(text)

    case ClientMessage(text) =>
      out ! text
  }

}
