package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.exception.domainexception.DomainException;

public class InvalidSkuException extends ProductException {

    public InvalidSkuException(String message) {
        super(message);
    }

    public InvalidSkuException(String message, Throwable cause) {
        super(message, cause);
    }
}
