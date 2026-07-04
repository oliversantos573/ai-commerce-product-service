package com.aicommerce.product.domain.valueobject.identifier;

import com.aicommerce.product.domain.valueobject.common.UuidValueObject;

import java.util.UUID;

public final class CustomerId extends UuidValueObject {

    public CustomerId(UUID value) {
        super(value);
    }

}