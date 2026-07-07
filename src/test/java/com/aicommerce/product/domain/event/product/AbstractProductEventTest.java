package com.aicommerce.product.domain.event.product;


import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class AbstractProductEventTest {

    private Instant FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void shouldCreateProductEventSuccessfully() {

        ProductId productId = new ProductId(UUID.randomUUID());
        Instant occurredAt = FIXED_INSTANT;

        TestProductEvent event = new TestProductEvent(productId, occurredAt);

        assertEquals(productId, event.getProductId());
        assertEquals(occurredAt, event.getOccurredAt());
        assertNotNull(event.getEventId());
    }

    @Test
    void shouldThrowExceptionWhenProductIdIsNull() {

        Instant occurredAt = FIXED_INSTANT;

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