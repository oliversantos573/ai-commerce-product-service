package com.aicommerce.product.application.query;

public record ListProductsQuery(

        int page,

        int size,

        String sortBy,

        String direction

) {
}