package com.aicommerce.product.domain.event.product;

import com.aicommerce.product.domain.event.DomainEvent;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import java.time.Instant;

public class ProductDeletedEvent extends AbstractProductEvent {

    public  ProductDeletedEvent(
            ProductId productId,
            Instant occurredAt) {

        super(productId, occurredAt);
    }

    @Override
    public String getEventType() {
        return "PRODUCT_DELETED";
    }
}