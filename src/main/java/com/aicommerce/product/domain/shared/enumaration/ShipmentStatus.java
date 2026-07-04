package com.aicommerce.product.domain.shared.enumaration;


/**
 * Represents the shipment lifecycle.
 */
public enum ShipmentStatus {

    PENDING,
    PICKING,
    PACKING,
    SHIPPED,
    IN_TRANSIT,
    DELIVERED,
    RETURNED

}