package com.aicommerce.product.domain.shared.enumaration;

/**
 * Represents the payment lifecycle.
 */
public enum PaymentStatus {

    PENDING,
    AUTHORIZED,
    CAPTURED,
    FAILED,
    REFUNDED,
    CANCELLED

}