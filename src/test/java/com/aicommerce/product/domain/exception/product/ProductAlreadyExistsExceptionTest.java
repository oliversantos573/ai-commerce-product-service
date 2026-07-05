package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductAlreadyExistsExceptionTest {

    @Test
    void shouldCreateException() {

        Sku sku = new Sku("ABC12345");

        ProductAlreadyExistsException exception =
                new ProductAlreadyExistsException(sku);

        assertEquals(
                "Product with SKU 'ABC12345' already exists.",
                exception.getMessage()
        );
    }
}