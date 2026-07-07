package com.aicommerce.product.domain.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    @Test
    void shouldCreateEntitySuccessfully() {

        UUID id = UUID.randomUUID();

        BaseEntityImpl entity = new BaseEntityImpl(id);

        assertEquals(id, entity.getId());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {

        assertThrows(
                NullPointerException.class,
                () -> new BaseEntityImpl(null)
        );
    }

    @Test
    void shouldBeEqualWhenIdsAreEqual() {

        UUID id = UUID.randomUUID();

        BaseEntityImpl entity1 = new BaseEntityImpl(id);
        BaseEntityImpl entity2 = new BaseEntityImpl(id);

        assertEquals(entity1, entity2);
    }

    @Test
    void shouldNotBeEqualWhenIdsAreDifferent() {

        BaseEntityImpl entity1 = new BaseEntityImpl(UUID.randomUUID());
        BaseEntityImpl entity2 = new BaseEntityImpl(UUID.randomUUID());

        assertNotEquals(entity1, entity2);
    }

    @Test
    void shouldHaveSameHashCodeWhenIdsAreEqual() {

        UUID id = UUID.randomUUID();

        BaseEntityImpl entity1 = new BaseEntityImpl(id);
        BaseEntityImpl entity2 = new BaseEntityImpl(id);

        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    private static class BaseEntityImpl extends BaseEntity<UUID> {

        BaseEntityImpl(UUID id) {
            super(id);
        }

    }

}