package com.aicommerce.product.domain.event.product;

import com.aicommerce.product.domain.event.DomainEvent;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import java.time.Instant;
import java.util.Objects;

/**
 * Base class for all Product domain events.
 */
public abstract class AbstractProductEvent extends DomainEvent {

    private final ProductId productId;

    protected AbstractProductEvent(
            ProductId productId,
            Instant occurredAt) {

        super(occurredAt);

        this.productId =
                Objects.requireNonNull(productId, "productId must not be null");
    }

    public ProductId getProductId() {
        return productId;
    }

}