package com.aicommerce.product.application.command;

import com.aicommerce.product.domain.valueobject.product.Sku;
import java.util.UUID;

public record CreateProductCommand(

        Sku sku,

        String name,

        String description,

        UUID brandId,

        UUID categoryId



) {
}