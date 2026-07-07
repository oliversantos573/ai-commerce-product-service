package com.aicommerce.product.application.command;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class UpdateProductCommandTest {

    @Test
    void shouldCreateCommand() {

        UUID id = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        UpdateProductCommand command = new UpdateProductCommand(
                id,
                "Notebook",
                "Notebook Gamer",
                brandId,
                categoryId
        );

        assertEquals(id, command.id());
        assertEquals("Notebook", command.name());
        assertEquals("Notebook Gamer", command.description());
        assertEquals(brandId, command.brandId());
        assertEquals(categoryId, command.categoryId());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {

        UUID id = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        UpdateProductCommand first = new UpdateProductCommand(
                id,
                "Notebook",
                "Notebook Gamer",
                brandId,
                categoryId
        );

        UpdateProductCommand second = new UpdateProductCommand(
                id,
                "Notebook",
                "Notebook Gamer",
                brandId,
                categoryId
        );

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldGenerateToString() {

        UpdateProductCommand command = new UpdateProductCommand(
                UUID.randomUUID(),
                "Notebook",
                "Notebook Gamer",
                UUID.randomUUID(),
                UUID.randomUUID()
        );

        assertNotNull(command.toString());
        assertTrue(command.toString().contains("Notebook"));
    }
}