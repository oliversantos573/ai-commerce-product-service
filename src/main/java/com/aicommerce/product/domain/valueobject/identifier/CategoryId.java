package com.aicommerce.product.domain.valueobject.identifier;

import com.aicommerce.product.domain.valueobject.common.UuidValueObject;

import java.util.UUID;

public final class CategoryId extends UuidValueObject {

    public CategoryId(UUID value) {
        super(value);
    }

}