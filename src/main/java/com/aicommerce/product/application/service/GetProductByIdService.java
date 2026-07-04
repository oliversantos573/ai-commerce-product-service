package com.aicommerce.product.application.service;

import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.mapper.ProductMapper;
import com.aicommerce.product.application.port.inbound.GetProductByIdUseCase;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.application.query.GetProductByIdQuery;
import com.aicommerce.product.domain.exception.product.ProductNotFoundException;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdService implements GetProductByIdUseCase {

    private final ProductRepositoryPort repository;

    public GetProductByIdService(ProductRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponse execute(GetProductByIdQuery query) {

        ProductId productId = new ProductId(query.productId());

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return ProductMapper.toResponse(product);
    }
}