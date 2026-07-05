package com.aicommerce.product.domain.exception.domainexception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {

        DomainException exception = new TestDomainException("Error");

        assertEquals("Error", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {

        RuntimeException cause = new RuntimeException("Cause");

        DomainException exception =
                new TestDomainException("Error", cause);

        assertEquals("Error", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    private static final class TestDomainException extends DomainException {

        TestDomainException(String message) {
            super(message);
        }

        TestDomainException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}