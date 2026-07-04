package com.aicommerce.product.adapters.inbound.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateProductRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @NotBlank
        @Size(max = 1000)
        String description,

        @NotNull
        UUID categoryId,

        @NotNull
        UUID brandId

) {
}