package com.aicommerce.product.domain.exception.product;

public class InvalidProductStatusException extends RuntimeException {

    public InvalidProductStatusException(String message) {
        super(message);
    }
}
