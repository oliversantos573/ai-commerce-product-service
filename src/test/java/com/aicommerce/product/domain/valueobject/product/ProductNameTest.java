package com.aicommerce.product.domain.valueobject.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductNameTest {

    @Test
    void shouldCreateProductName() {

        ProductName name = new ProductName("Notebook Gamer");

        assertEquals("Notebook Gamer", name.getValue());
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooShort() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ProductName("AB")
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooLong() {

        String value = "A".repeat(151);

        assertThrows(
                IllegalArgumentException.class,
                () -> new ProductName(value)
        );
    }
}