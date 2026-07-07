package com.aicommerce.product.application.dto.response;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductResponseTest {

    private static final Instant FIXED_INSTANT =
            Instant.parse("2020-01-01T00:00:00Z");


    @Test
    void shouldImplementEqualsAndHashCode() {

        UUID id = UUID.randomUUID();

        ProductResponse first =
                new ProductResponse(
                        id,
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        "ACTIVE",
                        FIXED_INSTANT,
                        FIXED_INSTANT
                );

        ProductResponse second =
                new ProductResponse(
                        id,
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        "ACTIVE",
                        FIXED_INSTANT,
                        FIXED_INSTANT
                );

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldGenerateToString() {

        ProductResponse response =
                new ProductResponse(
                        UUID.randomUUID(),
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        "ACTIVE",
                        FIXED_INSTANT,
                        FIXED_INSTANT
                );

        String result = response.toString();

        assertTrue(result.contains("Notebook"));
        assertTrue(result.contains("SKU-001"));
    }

}