package com.aicommerce.product.adapters.outbound.event;

import com.aicommerce.product.domain.event.DomainEvent;
import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SpringDomainEventPublisherAdapterTest {

    @Test
    void shouldPublishEventsWithoutThrowingException() {

        SpringDomainEventPublisherAdapter adapter =
                new SpringDomainEventPublisherAdapter();

        DomainEvent event = new FakeDomainEvent();

        assertDoesNotThrow(() ->
                adapter.publish(List.of(event))
        );
    }

    private static class FakeDomainEvent extends DomainEvent {

        protected FakeDomainEvent() {
            super(Instant.now());
        }

        @Override
        public ProductEventType getEventType() {
            return ProductEventType.PRODUCT_CREATED;
        }
    }
}