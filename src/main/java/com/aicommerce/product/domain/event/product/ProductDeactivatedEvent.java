package com.aicommerce.product.domain.event.product;

import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import java.time.Instant;

public class ProductDeactivatedEvent extends AbstractProductEvent {

    public ProductDeactivatedEvent(
            ProductId productId,
            Instant occurredAt) {

        super(productId, occurredAt);
    }

    @Override
    public ProductEventType getEventType() {
        return ProductEventType.PRODUCT_DEACTIVATED;
    }


}