package com.chatapp.controller

import com.chatapp.model.{ChatMessage, MessageType}
import org.springframework.messaging.handler.annotation.{MessageMapping, SendTo}
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ChatController(private val messagingTemplate: SimpMessagingTemplate) {

  @GetMapping(Array("/"))
  def index: String = "index"


  @GetMapping(Array("/chat"))
  def chat: String = "chat"


  @GetMapping(Array("/chat-ui"))
  def chatUI: String = "chat-ui"


  @MessageMapping(Array("/chat.sendMessage"))
  @SendTo(Array("/topic/public"))
  def sendMessage(message: ChatMessage): ChatMessage = message

  @MessageMapping(Array("/chat.addUser"))
  @SendTo(Array("/topic/public"))
  def addUser(message: ChatMessage, headerAccessor: SimpMessageHeaderAccessor): ChatMessage = {
    // Add username in web socket session
    headerAccessor.getSessionAttributes().put("username", message.sender)
    message.copy(messageType = MessageType.JOIN)
  }

  @MessageMapping(Array("/chat.private"))
  def sendPrivateMessage(message: ChatMessage): Unit = {
    // Send to recipient
    message.recipient.foreach { recipient =>
      messagingTemplate.convertAndSendToUser(
        recipient,
        "/queue/private",
        message
      )
    }
    
    // Send back to sender
    messagingTemplate.convertAndSendToUser(
      message.sender,
      "/queue/private",
      message
    )
  }
}