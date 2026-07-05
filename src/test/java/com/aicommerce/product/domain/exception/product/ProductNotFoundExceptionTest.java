package com.aicommerce.product.domain.exception.product;

import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductNotFoundExceptionTest {

    @Test
    void shouldCreateException() {

        ProductId productId =
                new ProductId(UUID.randomUUID());

        ProductNotFoundException exception =
                new ProductNotFoundException(productId);

        assertEquals(
                "Product not found. Id: " + productId.getValue(),
                exception.getMessage()
        );
    }
}