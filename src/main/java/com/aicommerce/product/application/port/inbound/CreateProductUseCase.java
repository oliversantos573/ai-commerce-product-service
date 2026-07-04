package com.aicommerce.product.application.port.inbound;

import com.aicommerce.product.application.command.CreateProductCommand;
import com.aicommerce.product.application.dto.response.CreateProductResponse;

public interface CreateProductUseCase {

    CreateProductResponse execute(CreateProductCommand command);

}