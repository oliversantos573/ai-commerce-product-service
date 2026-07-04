package com.aicommerce.product.adapters.outbound.uuid;

import com.aicommerce.product.application.port.outbound.UuidGeneratorPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JavaUuidGeneratorAdapter implements UuidGeneratorPort {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }

}