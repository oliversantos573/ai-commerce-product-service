package com.aicommerce.product.application.port.outbound;

import java.util.UUID;

/**
 * Generates unique identifiers.
 * The domain must not depend on UUID.randomUUID().
 */
public interface UuidGeneratorPort {

    UUID generate();

}