package com.aicommerce.product.domain.valueobject.identifier;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class CategoryIdTest {

    @Test
    void shouldCreateCategoryId() {

        UUID value = UUID.randomUUID();

        CategoryId categoryId = new CategoryId(value);

        assertEquals(value, categoryId.getValue());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {

        assertThrows(
                NullPointerException.class,
                () -> new CategoryId(null)
        );
    }

    @Test
    void shouldBeEqualWhenValuesAreEqual() {

        UUID value = UUID.randomUUID();

        CategoryId first = new CategoryId(value);
        CategoryId second = new CategoryId(value);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {

        CategoryId first = new CategoryId(UUID.randomUUID());
        CategoryId second = new CategoryId(UUID.randomUUID());

        assertNotEquals(first, second);
    }

    @Test
    void shouldReturnStringRepresentation() {

        UUID value = UUID.randomUUID();

        CategoryId categoryId = new CategoryId(value);

        assertEquals(value.toString(), categoryId.toString());
    }

}