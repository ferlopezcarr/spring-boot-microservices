package com.ferlopezcarr.microservices.productService.application.exceptions

import java.util.UUID

class ProductNotFoundException(
    id: UUID,
) : IllegalArgumentException("Product with id $id not found")
