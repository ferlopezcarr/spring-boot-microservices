package com.ferlopezcarr.microservices.orderService

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class OrderServiceApplicationTests {
    @Test
    fun contextLoads() {
    }
}
