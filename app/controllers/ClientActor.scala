package controllers

import akka.actor.{Actor, ActorRef}
import akka.event.LoggingReceive
import play.api.libs.json._

import scala.collection.immutable.Queue

class ClientActor(out: ActorRef, chat: ActorRef) extends Actor {

  chat ! Join

  override def postStop(): Unit = {
    chat ! Leave
  }

  override def receive: Receive = LoggingReceive {

    case json: JsValue =>
      chat ! ClientMessage(json)

    case ClientMessage(json: JsValue) =>
      out ! json

    case MessageList(messages: Queue[ClientMessage]) =>
      val result = JsObject(Map(
        "type" -> JsString("MessageList"),
        "data" -> JsArray(messages.map(_.json))
      ))
      out ! result

    case Users(count) =>
      val result = JsObject(Map(
        "type" -> JsString("Users"),
        "data" -> JsNumber(count)
      ))
      out ! result
  }

}
