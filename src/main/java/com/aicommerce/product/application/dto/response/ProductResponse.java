package com.aicommerce.product.application.dto.response;

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

}