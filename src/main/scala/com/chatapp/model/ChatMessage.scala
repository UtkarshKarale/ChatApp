package com.chatapp.model

import com.fasterxml.jackson.annotation.JsonProperty

case class ChatMessage(
  sender: String,
  content: String,
  @JsonProperty("messageType")
  messageType: String,
  @JsonProperty("recipient")
  recipient: Option[String] = None
)

object MessageType {
  val CHAT = "CHAT"
  val JOIN = "JOIN"
  val LEAVE = "LEAVE"
  val PRIVATE = "PRIVATE"
} 