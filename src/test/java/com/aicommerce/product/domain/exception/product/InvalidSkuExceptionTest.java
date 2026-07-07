package com.aicommerce.product.domain.exception.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidSkuExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {

        InvalidSkuException exception =
                new InvalidSkuException("Invalid SKU");

        assertEquals("Invalid SKU", exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithCause() {

        RuntimeException cause = new RuntimeException();

        InvalidSkuException exception =
                new InvalidSkuException("Invalid SKU", cause);

        assertEquals("Invalid SKU", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}