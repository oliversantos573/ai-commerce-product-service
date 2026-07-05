package com.aicommerce.product.domain.exception.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidDescriptionExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {

        InvalidDescriptionException exception =
                new InvalidDescriptionException("Invalid description");

        assertEquals("Invalid description", exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithCause() {

        RuntimeException cause = new RuntimeException();

        InvalidDescriptionException exception =
                new InvalidDescriptionException("Invalid description", cause);

        assertEquals("Invalid description", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}