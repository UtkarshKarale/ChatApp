package com.chatapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ChatApplication

object ChatApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ChatApplication], args: _*)
  }
}
