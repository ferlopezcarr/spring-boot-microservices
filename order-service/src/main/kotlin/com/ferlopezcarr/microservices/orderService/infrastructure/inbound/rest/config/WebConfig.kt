package com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebConfig {
    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule())
}
