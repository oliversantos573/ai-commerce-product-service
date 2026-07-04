package com.aicommerce.product.domain.valueobject.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for immutable String-based Value Objects.
 */
public abstract class StringValueObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String value;

    protected StringValueObject(String value) {

        this.value = Objects.requireNonNull(value, "Value must not be null")
                .trim();

        if (this.value.isBlank()) {
            throw new IllegalArgumentException("Value must not be blank.");
        }
    }

    public final String getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        StringValueObject other = (StringValueObject) obj;

        return value.equals(other.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public final String toString() {
        return value;
    }

}