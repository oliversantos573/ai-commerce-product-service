package com.aicommerce.product.domain.event.product;

import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public final class ProductPriceChangedEvent extends AbstractProductEvent {

    private final BigDecimal oldPrice;
    private final BigDecimal newPrice;

    public ProductPriceChangedEvent(
            ProductId productId,
            BigDecimal oldPrice,
            BigDecimal newPrice,
            Instant occurredAt) {

        super(productId, occurredAt);

        this.oldPrice = Objects.requireNonNull(oldPrice);
        this.newPrice = Objects.requireNonNull(newPrice);
    }

    @Override
    public ProductEventType getEventType() {
        return ProductEventType.PRODUCT_PRICE_CHANGED;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

}