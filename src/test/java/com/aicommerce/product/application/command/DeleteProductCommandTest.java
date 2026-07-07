package com.aicommerce.product.application.command;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class DeleteProductCommandTest {

    @Test
    void shouldCreateCommand() {

        UUID id = UUID.randomUUID();

        DeleteProductCommand command = new DeleteProductCommand(id);

        assertEquals(id, command.id());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {

        UUID id = UUID.randomUUID();

        DeleteProductCommand first = new DeleteProductCommand(id);
        DeleteProductCommand second = new DeleteProductCommand(id);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldGenerateToString() {

        DeleteProductCommand command =
                new DeleteProductCommand(UUID.randomUUID());

        assertNotNull(command.toString());
    }
}