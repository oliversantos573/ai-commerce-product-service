package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.exception.domainexception.DomainException;
import com.aicommerce.product.domain.valueobject.product.Sku;

public class ProductAlreadyExistsException extends DomainException {

    public ProductAlreadyExistsException(Sku sku) {
        super("Product with SKU '%s' already exists."
                .formatted(sku.getValue()));
    }

}