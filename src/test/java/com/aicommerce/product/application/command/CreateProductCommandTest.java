package com.aicommerce.product.application.command;

import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class CreateProductCommandTest {

    @Test
    void shouldCreateCommand() {

        Sku sku = new Sku("SKU-123");
        UUID categoryId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();

        CreateProductCommand command = new CreateProductCommand(
                sku,
                "Notebook",
                "Notebook Gamer",
                brandId,
                categoryId
        );

        assertEquals(sku, command.sku());
        assertEquals("Notebook", command.name());
        assertEquals("Notebook Gamer", command.description());
        assertEquals(brandId, command.brandId());
        assertEquals(categoryId, command.categoryId());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {

        Sku sku = new Sku("SKU-123");
        UUID categoryId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();

        CreateProductCommand first = new CreateProductCommand(
                sku,
                "Notebook",
                "Notebook Gamer",
                categoryId,
                brandId
        );

        CreateProductCommand second = new CreateProductCommand(
                sku,
                "Notebook",
                "Notebook Gamer",
                categoryId,
                brandId
        );

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldGenerateToString() {

        CreateProductCommand command = new CreateProductCommand(
                new Sku("SKU-123"),
                "Notebook",
                "Notebook Gamer",
                UUID.randomUUID(),
                UUID.randomUUID()
        );

        assertNotNull(command.toString());
        assertTrue(command.toString().contains("Notebook"));
    }
}