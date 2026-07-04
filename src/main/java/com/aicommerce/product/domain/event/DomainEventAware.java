package com.aicommerce.product.domain.event;

import java.util.List;

public interface DomainEventAware {

    List<DomainEvent> pullDomainEvents();

}
