package com.aicommerce.product.adapters.inbound.rest.mapper;

import com.aicommerce.product.adapters.inbound.rest.request.CreateProductRequest;
import com.aicommerce.product.application.command.CreateProductCommand;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductRequestMapperTest {

    @Test
    void shouldMapCreateRequestToCommand() {

        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        CreateProductRequest request =
                new CreateProductRequest(
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        brandId,
                        categoryId
                );

        CreateProductCommand command =
                ProductRequestMapper.toCommand(request);

        assertNotNull(command);

        assertEquals("SKU-001", command.sku().getValue());
        assertEquals("Notebook", command.name());
        assertEquals("Notebook Gamer", command.description());
        assertEquals(brandId, command.brandId());
        assertEquals(categoryId, command.categoryId());
    }
}