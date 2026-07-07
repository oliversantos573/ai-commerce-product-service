package com.aicommerce.product.application.service;

import com.aicommerce.product.application.command.UpdateProductCommand;
import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.mapper.ProductMapper;
import com.aicommerce.product.application.port.inbound.UpdateProductUseCase;
import com.aicommerce.product.application.port.outbound.ClockPort;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.domain.exception.product.ProductNotFoundException;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateProductService implements UpdateProductUseCase {

    private final ProductRepositoryPort repository;
    private final ClockPort clockPort;

    public UpdateProductService(
            ProductRepositoryPort repository,
            ClockPort clockPort) {

        this.repository = repository;
        this.clockPort = clockPort;
    }

    @Override
    public ProductResponse execute(UpdateProductCommand command) {

        Product product = repository.findById(
                        new ProductId(command.id()))
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                new ProductId(command.id())
                        ));

        product.update(
                new ProductName(command.name()),
                new Description(command.description()),
                new CategoryId(command.categoryId()),
                new BrandId(command.brandId()),
                clockPort.now()
        );

        return ProductMapper.toResponse(
                repository.save(product)
        );
    }

}