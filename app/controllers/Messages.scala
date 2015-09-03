package controllers

sealed trait ChatMessage
case object Join extends ChatMessage
case class ClientMessage(text: String) extends ChatMessage
case object Ack extends ChatMessage
