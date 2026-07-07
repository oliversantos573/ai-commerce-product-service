package com.aicommerce.product.domain.exception.product;

public class InvalidProductNameException extends ProductException {

    public InvalidProductNameException(String message) {
        super(message);
    }

    public InvalidProductNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
