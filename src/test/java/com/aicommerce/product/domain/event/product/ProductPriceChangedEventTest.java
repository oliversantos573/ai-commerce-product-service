package com.aicommerce.product.domain.event.product;

import com.aicommerce.product.domain.shared.enumaration.ProductEventType;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductPriceChangedEventTest {

    Instant FIXED_INSTANT = Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void shouldCreateProductPriceChangedEventSuccessfully() {

        ProductId productId = new ProductId(UUID.randomUUID());

        BigDecimal oldPrice = new BigDecimal("99.90");
        BigDecimal newPrice = new BigDecimal("129.90");

        Instant occurredAt = FIXED_INSTANT;

        ProductPriceChangedEvent event =
                new ProductPriceChangedEvent(
                        productId,
                        oldPrice,
                        newPrice,
                        occurredAt
                );

        assertEquals(ProductEventType.PRODUCT_PRICE_CHANGED, event.getEventType());
        assertEquals(productId, event.getProductId());
        assertEquals(oldPrice, event.getOldPrice());
        assertEquals(newPrice, event.getNewPrice());
        assertEquals(occurredAt, event.getOccurredAt());
        assertNotNull(event.getEventId());
    }

    @Test
    void shouldThrowExceptionWhenOldPriceIsNull() {

        ProductId productId = new ProductId(UUID.randomUUID());

        assertThrows(
                NullPointerException.class,
                () -> new ProductPriceChangedEvent(
                        productId,
                        null,
                        BigDecimal.TEN,
                        FIXED_INSTANT
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenNewPriceIsNull() {

        ProductId productId = new ProductId(UUID.randomUUID());

        assertThrows(
                NullPointerException.class,
                () -> new ProductPriceChangedEvent(
                        productId,
                        BigDecimal.TEN,
                        null,
                        FIXED_INSTANT
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenProductIdIsNull() {

        assertThrows(
                NullPointerException.class,
                () -> new ProductPriceChangedEvent(
                        null,
                        BigDecimal.ONE,
                        BigDecimal.TEN,
                        FIXED_INSTANT
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenOccurredAtIsNull() {

        ProductId productId = new ProductId(UUID.randomUUID());

        assertThrows(
                NullPointerException.class,
                () -> new ProductPriceChangedEvent(
                        productId,
                        BigDecimal.ONE,
                        BigDecimal.TEN,
                        null
                )
        );
    }

}