package com.aicommerce.product.adapters.inbound.rest.response;

public record FieldErrorResponse(

        String field,

        String message

) {
}