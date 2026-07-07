package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.valueobject.product.Sku;

public class ProductAlreadyExistsException extends ProductException {

    public ProductAlreadyExistsException(Sku sku) {
        super("Product with SKU '%s' already exists."
                .formatted(sku.getValue()));
    }

}