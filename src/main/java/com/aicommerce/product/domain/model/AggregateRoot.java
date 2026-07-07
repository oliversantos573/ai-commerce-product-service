package com.aicommerce.product.domain.model;

import com.aicommerce.product.domain.event.DomainEvent;
import com.aicommerce.product.domain.event.DomainEventAware;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base class for all Aggregate Roots.
 */
public abstract class AggregateRoot<ID>
        extends BaseEntity<ID>
        implements DomainEventAware {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot(ID id) {
        super(id);
    }

    protected final void registerEvent(DomainEvent event) {
        domainEvents.add(Objects.requireNonNull(event));
    }

    @Override
    public final List<DomainEvent> pullDomainEvents() {

        List<DomainEvent> events = List.copyOf(domainEvents);

        domainEvents.clear();

        return events;
    }

}