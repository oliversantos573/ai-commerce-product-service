package com.aicommerce.product.application.port.inbound;

import com.aicommerce.product.application.command.UpdateProductCommand;
import com.aicommerce.product.application.dto.response.ProductResponse;

public interface UpdateProductUseCase {

    ProductResponse execute(UpdateProductCommand command);

}