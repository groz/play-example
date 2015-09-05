package controllers

import akka.actor._
import akka.event.LoggingReceive

class Chat extends Actor {

  override def receive: Actor.Receive = process(Set.empty, List.empty)

  def process(subscribers: Set[ActorRef], messages: List[ClientMessage]): Receive = LoggingReceive {

    case Join =>
      context become process(subscribers + sender, messages)
      sender ! MessageList(messages)

    case Leave =>
      context become process(subscribers - sender, messages)

    case msg @ ClientMessage(json) =>
      (subscribers - sender).foreach { _ ! ClientMessage(json) }
      context become process(subscribers, msg :: messages)

  }
}
