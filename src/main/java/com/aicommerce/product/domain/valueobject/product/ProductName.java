package com.aicommerce.product.domain.valueobject.product;

import com.aicommerce.product.domain.valueobject.common.StringValueObject;

public final class ProductName extends StringValueObject {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 150;

    public ProductName(String value) {

        super(value);

        if (getValue().length() < MIN_LENGTH) {
            throw new IllegalArgumentException(
                    "Product name must contain at least " + MIN_LENGTH + " characters.");
        }

        if (getValue().length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Product name must contain at most " + MAX_LENGTH + " characters.");
        }
    }

}