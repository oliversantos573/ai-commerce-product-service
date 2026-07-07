package com.aicommerce.product.application.port.outbound;

import com.aicommerce.product.domain.event.DomainEvent;
import java.util.List;

public interface DomainEventPublisherPort {

    void publish(List<DomainEvent> events);

}