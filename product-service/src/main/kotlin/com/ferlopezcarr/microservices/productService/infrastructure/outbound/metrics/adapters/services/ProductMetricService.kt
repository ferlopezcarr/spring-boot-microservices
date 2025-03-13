package com.ferlopezcarr.microservices.productService.infrastructure.outbound.metrics.adapters.services

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ProductMetricService(
    @Value("\${metrics.service.name}") private val serviceName: String,
    private val metricRegistry: MeterRegistry,
) {
    private val productCreatedCounter = metricRegistry.counter("${serviceName}_created")

    fun notifyNewProduct() {
        productCreatedCounter.increment();
    }
}
