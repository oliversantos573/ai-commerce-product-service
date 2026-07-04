package com.aicommerce.product.application.command;

import java.util.UUID;

public record UpdateProductCommand(

        UUID id,

        String name,

        String description,

        UUID brandId,

        UUID categoryId

) {
}