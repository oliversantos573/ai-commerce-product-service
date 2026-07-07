package com.aicommerce.product.application.query;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListProductsQueryTest {

    @Test
    void shouldCreateQuery() {

        ListProductsQuery query =
                new ListProductsQuery(
                        0,
                        10,
                        "name",
                        "ASC"
                );

        assertEquals(0, query.page());
        assertEquals(10, query.size());
        assertEquals("name", query.sortBy());
        assertEquals("ASC", query.direction());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {

        ListProductsQuery first =
                new ListProductsQuery(
                        0,
                        10,
                        "name",
                        "ASC"
                );

        ListProductsQuery second =
                new ListProductsQuery(
                        0,
                        10,
                        "name",
                        "ASC"
                );

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldGenerateToString() {

        ListProductsQuery query =
                new ListProductsQuery(
                        0,
                        10,
                        "name",
                        "ASC"
                );

        assertNotNull(query.toString());

        String result = query.toString();

        assertTrue(result.contains("page"));
        assertTrue(result.contains("size"));
        assertTrue(result.contains("sortBy"));
        assertTrue(result.contains("direction"));
    }

}