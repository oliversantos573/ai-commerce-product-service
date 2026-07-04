package com.aicommerce.product.domain.valueobject.product;

import com.aicommerce.product.domain.valueobject.common.StringValueObject;

public final class Description extends StringValueObject {

    private static final int MAX_LENGTH = 1000;

    public Description(String value) {

        super(value);

        if (getValue().length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Description must contain at most " + MAX_LENGTH + " characters.");
        }
    }

}