package com.aicommerce.product.domain.event;

import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

class DomainEventTest {

    Instant FIXED_INSTANT = Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void shouldGenerateEventId() {
        DomainEventImpl event = new DomainEventImpl(FIXED_INSTANT);
        assertNotNull(event.getEventId());
    }

    @Test
    void shouldStoreOccurredAt() {
        DomainEventImpl event = new DomainEventImpl(FIXED_INSTANT);
        assertEquals(FIXED_INSTANT, event.getOccurredAt());
    }

    @Test
    void shouldReturnEventType() {
        DomainEventImpl event = new DomainEventImpl(FIXED_INSTANT);
        assertEquals(ProductEventType.PRODUCT_ACTIVATED, event.getEventType());
    }

    private static class DomainEventImpl extends DomainEvent {
        DomainEventImpl(Instant occurredAt) {
            super(occurredAt);
        }

        @Override
        public ProductEventType getEventType() {
            return  ProductEventType.PRODUCT_ACTIVATED;
        }
    }
}
