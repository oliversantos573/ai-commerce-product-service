package com.aicommerce.product.adapters.inbound.rest.mapper;

import com.aicommerce.product.adapters.inbound.rest.request.CreateProductRequest;
import com.aicommerce.product.adapters.inbound.rest.request.UpdateProductRequest;
import com.aicommerce.product.application.command.CreateProductCommand;
import com.aicommerce.product.application.command.UpdateProductCommand;
import com.aicommerce.product.domain.valueobject.product.Sku;
import java.util.UUID;

public final class ProductRequestMapper {

    private ProductRequestMapper() {
    }

    public static CreateProductCommand toCommand(CreateProductRequest request) {

        return new CreateProductCommand(
                new Sku(request.sku()),
                request.name(),
                request.description(),
                request.brandId(),
                request.categoryId()
        );

    }

    public static UpdateProductCommand toCommand(
            UUID id,
            UpdateProductRequest request) {

        return new UpdateProductCommand(
                id,
                request.name(),
                request.description(),
                request.brandId(),
                request.categoryId()
        );
    }

}