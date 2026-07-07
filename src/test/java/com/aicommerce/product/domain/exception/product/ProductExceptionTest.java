package com.aicommerce.product.domain.exception.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {

        ProductException exception =
                new TestProductException("Error");

        assertEquals("Error", exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {

        RuntimeException cause = new RuntimeException();

        ProductException exception =
                new TestProductException("Error", cause);

        assertEquals("Error", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    private static final class TestProductException extends ProductException {

        TestProductException(String message) {
            super(message);
        }

        TestProductException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}