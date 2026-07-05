package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.exception.domainexception.DomainException;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;

public class ProductNotFoundException extends ProductException {

    public ProductNotFoundException(ProductId productId) {
        super("Product not found. Id: " + productId.getValue());
    }
}