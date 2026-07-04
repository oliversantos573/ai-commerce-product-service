package com.aicommerce.product.domain.valueobject.identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * Base class for all domain identifiers.
 */
public abstract class Identifier {

    private final UUID value;

    protected Identifier(UUID value) {
        this.value = Objects.requireNonNull(value, "Identifier value must not be null");
    }

    public UUID getValue() {
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

        Identifier other = (Identifier) obj;

        return value.equals(other.value);
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }

}