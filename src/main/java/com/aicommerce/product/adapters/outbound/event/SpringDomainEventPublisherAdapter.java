package com.aicommerce.product.adapters.outbound.event;

import com.aicommerce.product.application.port.outbound.DomainEventPublisherPort;
import com.aicommerce.product.domain.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringDomainEventPublisherAdapter implements DomainEventPublisherPort {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SpringDomainEventPublisherAdapter.class);

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent event) {
        LOGGER.info(
                "Publishing domain event: type={}, occurredAt={}, eventId={}",
                event.getEventType(),
                event.getOccurredAt(),
                event.getEventId()
        );
    }
}