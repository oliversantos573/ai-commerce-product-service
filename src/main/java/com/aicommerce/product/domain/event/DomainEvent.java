package com.aicommerce.product.domain.event;

import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public abstract class DomainEvent {

    private final UUID eventId;
    private final Instant occurredAt;

    protected DomainEvent(Instant occurredAt) {
        this.eventId = UUID.randomUUID();
        this.occurredAt = Objects.requireNonNull(
                occurredAt,
                "occurredAt must not be null"
        );
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public abstract ProductEventType getEventType();
}