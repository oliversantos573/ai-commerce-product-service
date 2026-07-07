package com.aicommerce.product.domain.valueobject.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    void shouldCreateDescription() {

        Description description =
                new Description("Gaming notebook");

        assertEquals("Gaming notebook", description.getValue());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsTooLong() {

        String value = "A".repeat(1001);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Description(value)
        );
    }
}