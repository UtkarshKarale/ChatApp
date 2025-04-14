package com.chatapp.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{CorsRegistry, WebMvcConfigurer}

@Configuration
class WebConfig extends WebMvcConfigurer {

  override def addCorsMappings(registry: CorsRegistry): Unit = {
    registry.addMapping("/**")
      .allowedOriginPatterns("*")
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
      .allowedHeaders("*")
      .allowCredentials(true)
      .maxAge(3600)
  }
} 