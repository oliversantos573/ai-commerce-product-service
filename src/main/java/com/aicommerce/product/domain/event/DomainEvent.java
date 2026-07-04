package com.aicommerce.product.domain.event;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {

    private final UUID eventId;

    private final Instant occurredAt;

    protected DomainEvent(Instant occurredAt) {
        this.eventId = UUID.randomUUID();
        this.occurredAt = occurredAt;
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public abstract String getEventType();

}