package com.ferlopezcarr.microservices.productService.application.exceptions

class ProductAlreadyExistsException(
    name: String,
) : IllegalArgumentException("Product with name $name already exists")
