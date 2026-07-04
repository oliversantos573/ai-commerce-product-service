package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.exception.domainexception.DomainException;

public class InvalidDescriptionException extends DomainException {

    public InvalidDescriptionException(String message) {
        super(message);
    }

    public InvalidDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
