package com.aicommerce.product.domain.event.product;


import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AbstractProductEventTest {

    @Test
    void shouldCreateProductEventSuccessfully() {

        ProductId productId = new ProductId(UUID.randomUUID());
        Instant occurredAt = Instant.now();

        TestProductEvent event = new TestProductEvent(productId, occurredAt);

        assertEquals(productId, event.getProductId());
        assertEquals(occurredAt, event.getOccurredAt());
        assertNotNull(event.getEventId());
    }

    @Test
    void shouldThrowExceptionWhenProductIdIsNull() {

        Instant occurredAt = Instant.now();

        assertThrows(
                NullPointerException.class,
                () -> new TestProductEvent(null, occurredAt)
        );
    }

    @Test
    void shouldThrowExceptionWhenOccurredAtIsNull() {

        ProductId productId = new ProductId(UUID.randomUUID());

        assertThrows(
                NullPointerException.class,
                () -> new TestProductEvent(productId, null)
        );
    }

    private static final class TestProductEvent extends AbstractProductEvent {

        TestProductEvent(ProductId productId, Instant occurredAt) {
            super(productId, occurredAt);
        }

        @Override
        public ProductEventType getEventType() {
            return ProductEventType.PRODUCT_CREATED;
        }
    }
}