package com.aicommerce.product.application.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateProductResponseTest {

    @Test
    void shouldCreateResponse() {

        CreateProductResponse response =
                new CreateProductResponse(null);

        assertEquals(null, response.product());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {

        CreateProductResponse first =
                new CreateProductResponse(null);

        CreateProductResponse second =
                new CreateProductResponse(null);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldGenerateToString() {

        CreateProductResponse response =
                new CreateProductResponse(null);

        assertEquals(
                "CreateProductResponse[product=null]",
                response.toString()
        );
    }

}