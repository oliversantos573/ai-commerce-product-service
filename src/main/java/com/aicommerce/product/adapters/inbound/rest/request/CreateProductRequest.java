package com.aicommerce.product.adapters.inbound.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CreateProductRequest(

        @NotBlank(message = "SKU is required")
        @Size(max = 50, message = "SKU must contain at most 50 characters")
        String sku,

        @NotBlank(message = "Name is required")
        @Size(max = 150, message = "Name must contain at most 150 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description must contain at most 1000 characters")
        String description,

        @NotNull(message = "BrandId is required")
        UUID brandId,

        @NotNull(message = "CategoryId is required")
        UUID categoryId

) {
}