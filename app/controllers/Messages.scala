package controllers

import play.api.libs.json._

import scala.collection.immutable.Queue

case object Join
case object Leave
case class ClientMessage(json: JsValue)
case class MessageList(messages: Queue[ClientMessage])
case object Ack
case class Users(count: Int)