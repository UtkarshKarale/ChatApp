package com.chatapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.{EnableWebSocketMessageBroker, StompEndpointRegistry, WebSocketMessageBrokerConfigurer}
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig extends WebSocketMessageBrokerConfigurer {

  @Bean
  def objectMapper: ObjectMapper = {
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper
  }

  @Bean
  def messageConverter: MappingJackson2MessageConverter = {
    val converter = new MappingJackson2MessageConverter()
    converter.setObjectMapper(objectMapper)
    converter
  }

  override def configureMessageBroker(registry: MessageBrokerRegistry): Unit = {
    registry.enableSimpleBroker("/topic")
    registry.setApplicationDestinationPrefixes("/app")
    registry.setUserDestinationPrefix("/user")
  }

  override def registerStompEndpoints(registry: StompEndpointRegistry): Unit = {
    registry.addEndpoint("/ws")
      .setAllowedOriginPatterns("*")
      .withSockJS()
  }

  override def configureWebSocketTransport(registration: WebSocketTransportRegistration): Unit = {
    registration.setMessageSizeLimit(8192) // 8KB
      .setSendBufferSizeLimit(512 * 1024) // 512KB
      .setSendTimeLimit(20 * 1000) // 20 seconds
  }
} 