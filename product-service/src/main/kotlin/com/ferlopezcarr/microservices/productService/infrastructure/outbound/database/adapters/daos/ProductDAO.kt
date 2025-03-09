package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.daos

import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.models.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface ProductDAO : MongoRepository<ProductEntity, UUID>
