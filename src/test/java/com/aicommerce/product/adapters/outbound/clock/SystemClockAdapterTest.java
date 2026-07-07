package com.aicommerce.product.adapters.outbound.clock;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.assertj.core.api.Assertions.assertThat;

class SystemClockAdapterTest {

    @Test
    void shouldReturnCurrentInstant() {

        SystemClockAdapter adapter = new SystemClockAdapter();

        Instant before = Instant.now();

        Instant now = adapter.now();

        Instant after = Instant.now();

        assertThat(now)
                .isAfterOrEqualTo(before)
                .isBeforeOrEqualTo(after);
    }
}