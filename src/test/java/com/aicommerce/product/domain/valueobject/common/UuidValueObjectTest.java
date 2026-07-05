package com.aicommerce.product.domain.valueobject.common;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UuidValueObjectTest {

    @Test
    void shouldCreateValueObject() {

        UUID id = UUID.randomUUID();

        TestUuidValueObject valueObject =
                new TestUuidValueObject(id);

        assertEquals(id, valueObject.getValue());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {

        assertThrows(
                NullPointerException.class,
                () -> new TestUuidValueObject(null)
        );
    }

    @Test
    void shouldBeEqualWhenValuesAreEqual() {

        UUID id = UUID.randomUUID();

        TestUuidValueObject first =
                new TestUuidValueObject(id);

        TestUuidValueObject second =
                new TestUuidValueObject(id);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {

        TestUuidValueObject first =
                new TestUuidValueObject(UUID.randomUUID());

        TestUuidValueObject second =
                new TestUuidValueObject(UUID.randomUUID());

        assertNotEquals(first, second);
    }

    @Test
    void shouldReturnStringRepresentation() {

        UUID id = UUID.randomUUID();

        TestUuidValueObject valueObject =
                new TestUuidValueObject(id);

        assertEquals(id.toString(), valueObject.toString());
    }

    private static final class TestUuidValueObject
            extends UuidValueObject {

        TestUuidValueObject(UUID value) {
            super(value);
        }
    }
}