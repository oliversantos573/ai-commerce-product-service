package com.aicommerce.product.domain.valueobject.identifier;

import com.aicommerce.product.domain.valueobject.common.UuidValueObject;

import java.util.UUID;

public final class BrandId extends UuidValueObject {

    public BrandId(UUID value) {
        super(value);
    }

}