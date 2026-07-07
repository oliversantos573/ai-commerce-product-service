package com.aicommerce.product.adapters.outbound.clock;

import com.aicommerce.product.application.port.outbound.ClockPort;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class SystemClockAdapter implements ClockPort {

    @Override
    public Instant now() {
        return Instant.now();
    }

}