package com.aicommerce.product.application.query;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class GetProductByIdQueryTest {

    @Test
    void shouldCreateQuery() {

        UUID productId = UUID.randomUUID();

        GetProductByIdQuery query =
                new GetProductByIdQuery(productId);

        assertEquals(productId, query.productId());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {

        UUID productId = UUID.randomUUID();

        GetProductByIdQuery first =
                new GetProductByIdQuery(productId);

        GetProductByIdQuery second =
                new GetProductByIdQuery(productId);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldGenerateToString() {

        GetProductByIdQuery query =
                new GetProductByIdQuery(UUID.randomUUID());

        assertNotNull(query.toString());
        assertTrue(query.toString().contains("productId"));
    }

}