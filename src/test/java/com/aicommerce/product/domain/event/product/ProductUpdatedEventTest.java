package com.aicommerce.product.domain.event.product;

import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductUpdatedEventTest {

    Instant FIXED_INSTANT = Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void shouldCreateProductUpdatedEventSuccessfully() {

        ProductId productId = new ProductId(UUID.randomUUID());
        Instant occurredAt = FIXED_INSTANT;

        ProductUpdatedEvent event =
                new ProductUpdatedEvent(productId, occurredAt);

        assertEquals(ProductEventType.PRODUCT_UPDATED, event.getEventType());
        assertEquals(productId, event.getProductId());
        assertEquals(occurredAt, event.getOccurredAt());
        assertNotNull(event.getEventId());
    }
}