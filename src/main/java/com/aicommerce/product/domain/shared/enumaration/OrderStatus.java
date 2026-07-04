package com.aicommerce.product.domain.shared.enumaration;

/**
 * Represents the order lifecycle.
 */
public enum OrderStatus {

    CREATED,
    PENDING_PAYMENT,
    PAID,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    REFUNDED

}