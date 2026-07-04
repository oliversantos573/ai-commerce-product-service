package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.exception.domainexception.DomainException;

public abstract class ProductException extends DomainException {

    protected ProductException(String message) {
        super(message);
    }

    protected ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}