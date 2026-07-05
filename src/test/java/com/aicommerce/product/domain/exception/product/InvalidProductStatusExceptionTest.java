package com.aicommerce.product.domain.exception.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidProductStatusExceptionTest {

    @Test
    void shouldCreateException() {

        InvalidProductStatusException exception =
                new InvalidProductStatusException("Invalid status");

        assertEquals("Invalid status", exception.getMessage());
    }
}