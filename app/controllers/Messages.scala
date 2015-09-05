package controllers

import play.api.libs.json._

case object Join
case object Leave
case class ClientMessage(json: JsValue)
case class MessageList(messages: List[ClientMessage])
case object Ack
