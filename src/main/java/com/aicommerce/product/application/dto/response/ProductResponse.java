package com.aicommerce.product.application.dto.response;

import com.aicommerce.product.domain.model.Product;

import java.time.Instant;
import java.util.UUID;

public record ProductResponse(

        UUID id,
        String sku,
        String name,
        String description,
        String status,
        Instant createdAt,
        Instant updatedAt

) {

    public static ProductResponse from(Product product) {

        return new ProductResponse(
                product.getId().getValue(),
                product.getSku().getValue(),
                product.getName().getValue(),
                product.getDescription().getValue(),
                product.getStatus().name(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

}