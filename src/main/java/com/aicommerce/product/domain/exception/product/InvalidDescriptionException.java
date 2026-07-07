package com.aicommerce.product.domain.exception.product;

public class InvalidDescriptionException extends ProductException {

    public InvalidDescriptionException(String message) {
        super(message);
    }

    public InvalidDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
