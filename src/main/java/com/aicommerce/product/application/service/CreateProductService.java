package com.aicommerce.product.application.service;

import com.aicommerce.product.application.command.CreateProductCommand;
import com.aicommerce.product.application.dto.response.CreateProductResponse;
import com.aicommerce.product.application.mapper.ProductMapper;
import com.aicommerce.product.application.port.inbound.CreateProductUseCase;
import com.aicommerce.product.application.port.outbound.ClockPort;
import com.aicommerce.product.application.port.outbound.DomainEventPublisherPort;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.application.port.outbound.UuidGeneratorPort;
import com.aicommerce.product.domain.exception.product.ProductAlreadyExistsException;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort repository;
    private final UuidGeneratorPort uuidGenerator;
    private final ClockPort clock;
    private final DomainEventPublisherPort eventPublisher;

    public CreateProductService(
            ProductRepositoryPort repository,
            UuidGeneratorPort uuidGenerator,
            ClockPort clock,
            DomainEventPublisherPort eventPublisher) {

        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.clock = clock;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CreateProductResponse execute(CreateProductCommand command) {

        Sku sku = new Sku(command.sku().getValue());

        if (repository.existsBySku(sku.getValue())) {
            throw new ProductAlreadyExistsException(sku);
        }

        Instant now = clock.now();

        Product product = Product.create(
                new ProductId(uuidGenerator.generate()),
                sku,
                new ProductName(command.name()),
                new Description(command.description()),
                new BrandId(command.brandId()),
                new CategoryId(command.categoryId()),
                now
        );

        Product savedProduct = repository.save(product);

        eventPublisher.publish(savedProduct.pullDomainEvents());

        return ProductMapper.toCreateResponse(savedProduct);
    }
}