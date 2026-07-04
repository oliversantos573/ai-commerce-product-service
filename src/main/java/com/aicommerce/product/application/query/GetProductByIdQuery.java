package com.aicommerce.product.application.query;

import java.util.UUID;

public record GetProductByIdQuery(

        UUID productId

) {
}