package com.aicommerce.product.domain.valueobject.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValueObjectTest {

    @Test
    void shouldCreateValueObject() {

        TestStringValueObject valueObject =
                new TestStringValueObject("Product");

        assertEquals("Product", valueObject.getValue());
    }

    @Test
    void shouldTrimValue() {

        TestStringValueObject valueObject =
                new TestStringValueObject("  Product  ");

        assertEquals("Product", valueObject.getValue());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {

        assertThrows(
                NullPointerException.class,
                () -> new TestStringValueObject(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenValueIsBlank() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new TestStringValueObject("   ")
        );
    }

    @Test
    void shouldBeEqualWhenValuesAreEqual() {

        TestStringValueObject first =
                new TestStringValueObject("ABC");

        TestStringValueObject second =
                new TestStringValueObject("ABC");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {

        TestStringValueObject first =
                new TestStringValueObject("ABC");

        TestStringValueObject second =
                new TestStringValueObject("XYZ");

        assertNotEquals(first, second);
    }

    @Test
    void shouldReturnStringRepresentation() {

        TestStringValueObject valueObject =
                new TestStringValueObject("Product");

        assertEquals("Product", valueObject.toString());
    }

    private static final class TestStringValueObject
            extends StringValueObject {

        TestStringValueObject(String value) {
            super(value);
        }
    }
}