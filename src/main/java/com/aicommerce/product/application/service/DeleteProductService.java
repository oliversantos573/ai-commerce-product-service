package com.aicommerce.product.application.service;

import com.aicommerce.product.application.command.DeleteProductCommand;
import com.aicommerce.product.application.port.inbound.DeleteProductUseCase;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.domain.exception.product.ProductNotFoundException;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductService implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void execute(DeleteProductCommand command) {

        ProductId productId = new ProductId(command.id());

        productRepositoryPort.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(productId));

        productRepositoryPort.deleteById(productId);
    }

}