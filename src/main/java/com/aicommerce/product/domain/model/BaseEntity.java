package com.aicommerce.product.domain.model;

import java.util.Objects;

/**
 * Base class for all domain entities.
 *
 * @param <ID> Entity identifier type.
 */
public abstract class BaseEntity<ID> {

    private final ID id;

    protected BaseEntity(ID id) {
        this.id = Objects.requireNonNull(id, "Entity id must not be null");
    }

    public final ID getId() {
        return id;
    }

    @Override
    public final boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BaseEntity<?> other = (BaseEntity<?>) obj;

        return id.equals(other.id);
    }

    @Override
    public final int hashCode() {
        return id.hashCode();
    }
}