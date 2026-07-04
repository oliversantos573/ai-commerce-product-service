package com.aicommerce.product.domain.valueobject.common;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Base class for immutable UUID-based Value Objects.
 */
public abstract class UuidValueObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UUID value;

    protected UuidValueObject(UUID value) {
        this.value = Objects.requireNonNull(value, "Value must not be null");
    }

    public final UUID getValue() {
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

        UuidValueObject other = (UuidValueObject) obj;

        return value.equals(other.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public final String toString() {
        return value.toString();
    }

}