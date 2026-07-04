package com.aicommerce.product.application.mapper;

import com.aicommerce.product.application.dto.response.CreateProductResponse;
import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.domain.model.Product;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static ProductResponse toResponse(Product product) {

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

    public static CreateProductResponse toCreateResponse(Product product) {
        return new CreateProductResponse(
                toResponse(product)
        );
    }

}