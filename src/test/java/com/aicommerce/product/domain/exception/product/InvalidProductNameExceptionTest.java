package com.aicommerce.product.domain.exception.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidProductNameExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {

        InvalidProductNameException exception =
                new InvalidProductNameException("Invalid product name");

        assertEquals("Invalid product name", exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {

        RuntimeException cause = new RuntimeException("Cause");

        InvalidProductNameException exception =
                new InvalidProductNameException(
                        "Invalid product name",
                        cause
                );

        assertAll(
                () -> assertEquals("Invalid product name", exception.getMessage()),
                () -> assertEquals(cause, exception.getCause())
        );
    }

}