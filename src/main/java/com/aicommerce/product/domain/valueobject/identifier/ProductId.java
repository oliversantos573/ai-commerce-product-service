package com.aicommerce.product.domain.valueobject.identifier;

import com.aicommerce.product.domain.valueobject.common.UuidValueObject;
import java.util.UUID;

public final class ProductId extends UuidValueObject {

    public ProductId(UUID value) {
        super(value);
    }

}