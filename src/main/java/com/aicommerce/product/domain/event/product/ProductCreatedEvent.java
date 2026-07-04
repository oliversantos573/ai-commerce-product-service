package com.aicommerce.product.domain.event.product;

import com.aicommerce.product.domain.valueobject.identifier.ProductId;

import java.time.Instant;

public final class ProductCreatedEvent extends AbstractProductEvent {

    public ProductCreatedEvent(
            ProductId productId,
            Instant occurredAt) {

        super(productId, occurredAt);
    }

    @Override
    public String getEventType() {
        return "PRODUCT_CREATED";
    }

}