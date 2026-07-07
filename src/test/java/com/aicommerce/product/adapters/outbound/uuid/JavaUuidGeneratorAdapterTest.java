package com.aicommerce.product.adapters.outbound.uuid;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class JavaUuidGeneratorAdapterTest {

    @Test
    void shouldGenerateUuid() {

        JavaUuidGeneratorAdapter adapter =
                new JavaUuidGeneratorAdapter();

        UUID uuid = adapter.generate();

        assertThat(uuid).isNotNull();
    }

    @Test
    void shouldGenerateDifferentUuids() {

        JavaUuidGeneratorAdapter adapter =
                new JavaUuidGeneratorAdapter();

        UUID first = adapter.generate();
        UUID second = adapter.generate();

        assertThat(first).isNotEqualTo(second);
    }
}