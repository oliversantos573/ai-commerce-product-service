package com.aicommerce.product.domain.valueobject.identifier;

import com.aicommerce.product.domain.valueobject.common.UuidValueObject;

import java.util.UUID;

public final class OrderId extends UuidValueObject {

    public OrderId(UUID value) {
        super(value);
    }

}