package com.ferlopezcarr.microservices.apiGateway.routes

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.RequestPredicates

@Configuration
class Routes(
    @Value("\${product-service.url}")
    private val productServiceUrl: String
) {
    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator =
        GatewayRouterFunctions.route("product_service")
            .route(RequestPredicates.path("/product"), HandlerFunctions.http(productServiceUrl))
            .route(RequestPredicates.path("/products"), HandlerFunctions.http(productServiceUrl))
            .build()
}