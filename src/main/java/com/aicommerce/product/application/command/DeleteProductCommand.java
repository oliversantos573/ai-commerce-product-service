package com.aicommerce.product.application.command;

import java.util.UUID;

public record DeleteProductCommand(

        UUID id

) {
}