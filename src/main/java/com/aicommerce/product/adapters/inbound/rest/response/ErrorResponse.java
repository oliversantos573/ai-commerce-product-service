package com.aicommerce.product.adapters.inbound.rest.response;

import java.time.Instant;

public record ErrorResponse(

        Instant timestamp,

        int status,

        String error,

        String message,

        String path

) {
}