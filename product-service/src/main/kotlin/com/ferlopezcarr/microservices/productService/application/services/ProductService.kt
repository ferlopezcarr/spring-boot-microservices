package com.ferlopezcarr.microservices.productService.application.services

import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.ports.ProductRepository
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.metrics.adapters.services.ProductMetricService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productMetricService: ProductMetricService,
) {
    fun getById(id: UUID): Product = productRepository.getById(id)

    fun create(product: NewProduct): Product = productRepository.save(product).also { productMetricService.notifyNewProduct() }

    fun getAll(): List<Product> = productRepository.getAll()
}
