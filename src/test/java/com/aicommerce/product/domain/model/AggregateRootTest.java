package com.aicommerce.product.domain.model;

import com.aicommerce.product.domain.event.DomainEvent;
import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AggregateRootTest {

    @Test
    void shouldRegisterDomainEvent() {

        AggregateRootImpl aggregate = new AggregateRootImpl(UUID.randomUUID());

        aggregate.register(new DomainEventImpl(Instant.now()));

        assertEquals(1, aggregate.pullDomainEvents().size());
    }

    @Test
    void shouldReturnRegisteredEvents() {

        AggregateRootImpl aggregate = new AggregateRootImpl(UUID.randomUUID());

        DomainEvent event = new DomainEventImpl(Instant.now());

        aggregate.register(event);

        List<DomainEvent> events = aggregate.pullDomainEvents();

        assertEquals(event, events.getFirst());
    }

    @Test
    void shouldClearEventsAfterPull() {

        AggregateRootImpl aggregate = new AggregateRootImpl(UUID.randomUUID());

        aggregate.register(new DomainEventImpl(Instant.now()));

        aggregate.pullDomainEvents();

        assertTrue(aggregate.pullDomainEvents().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenEventIsNull() {

        AggregateRootImpl aggregate = new AggregateRootImpl(UUID.randomUUID());

        assertThrows(
                NullPointerException.class,
                () -> aggregate.register(null)
        );
    }

    private static class AggregateRootImpl extends AggregateRoot<UUID> {

        AggregateRootImpl(UUID id) {
            super(id);
        }

        void register(DomainEvent event) {
            registerEvent(event);
        }

    }

    private static class DomainEventImpl extends DomainEvent {

        DomainEventImpl(Instant occurredAt) {
            super(occurredAt);
        }

        @Override
        public ProductEventType getEventType() {
            return ProductEventType.PRODUCT_UPDATED;
        }

    }

}