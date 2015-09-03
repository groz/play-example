package controllers

import io.circe._, io.circe.generic.auto._, io.circe.jawn._, io.circe.syntax._

object ChatApp extends App {

  val msg: ChatMessage = ClientMessage("hello")
  val str: String = msg.asJson.noSpaces
  val back = decode[ChatMessage](str)

  println(back)
}
