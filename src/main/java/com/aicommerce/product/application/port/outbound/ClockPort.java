package com.aicommerce.product.application.port.outbound;

import java.time.Instant;

public interface ClockPort {

    Instant now();

}