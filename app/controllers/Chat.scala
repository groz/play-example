package controllers

import akka.actor._
import akka.event.LoggingReceive

import scala.collection.immutable.Queue

class Chat extends Actor {

  override def receive: Actor.Receive = process(Set.empty, Queue.empty)

  def process(subscribers: Set[ActorRef], messages: Queue[ClientMessage]): Receive = LoggingReceive {

    case Join =>
      val newSubscribers = subscribers + sender
      sender ! MessageList(messages)
      newSubscribers.foreach(_ ! Users(newSubscribers.size))
      context become process(newSubscribers, messages)

    case Leave =>
      val newSubscribers = subscribers - sender
      subscribers.foreach(_ ! Users(newSubscribers.size))
      context become process(newSubscribers, messages)


    case msg @ ClientMessage(json) =>
      (subscribers - sender).foreach { _ ! ClientMessage(json) }
      context become process(subscribers, messages enqueue msg)

  }
}
