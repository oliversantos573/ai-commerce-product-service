package com.aicommerce.product.domain.valueobject.identifier;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BrandIdTest {

    @Test
    void shouldCreateBrandId() {

        UUID value = UUID.randomUUID();

        BrandId brandId = new BrandId(value);

        assertEquals(value, brandId.getValue());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {

        assertThrows(
                NullPointerException.class,
                () -> new BrandId(null)
        );
    }

    @Test
    void shouldBeEqualWhenValuesAreEqual() {

        UUID value = UUID.randomUUID();

        BrandId first = new BrandId(value);
        BrandId second = new BrandId(value);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {

        BrandId first = new BrandId(UUID.randomUUID());
        BrandId second = new BrandId(UUID.randomUUID());

        assertNotEquals(first, second);
    }

    @Test
    void shouldReturnStringRepresentation() {

        UUID value = UUID.randomUUID();

        BrandId brandId = new BrandId(value);

        assertEquals(value.toString(), brandId.toString());
    }

}