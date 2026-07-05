package com.aicommerce.product.domain.valueobject.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkuTest {

    @Test
    void shouldCreateSku() {

        Sku sku = new Sku("NOTEBOOK-001");

        assertEquals("NOTEBOOK-001", sku.getValue());
    }

    @Test
    void shouldThrowExceptionWhenSkuIsTooShort() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Sku("AB")
        );
    }

    @Test
    void shouldThrowExceptionWhenSkuIsTooLong() {

        String value = "A".repeat(51);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Sku(value)
        );
    }
}