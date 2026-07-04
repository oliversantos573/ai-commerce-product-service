package com.aicommerce.product.application.port.inbound;

import com.aicommerce.product.application.command.DeleteProductCommand;

public interface DeleteProductUseCase {

    void execute(DeleteProductCommand command);

}