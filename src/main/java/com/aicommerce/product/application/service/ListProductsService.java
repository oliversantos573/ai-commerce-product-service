package com.aicommerce.product.application.service;

import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.port.inbound.ListProductsUseCase;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.application.query.ListProductsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListProductsService implements ListProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;


    public ListProductsService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Page<ProductResponse> execute(ListProductsQuery query) {

        Sort.Direction direction = Sort.Direction.fromString(query.direction());

        PageRequest pageable = PageRequest.of(
                query.page(),
                query.size(),
                Sort.by(direction, query.sortBy())
        );

        return productRepositoryPort
                 .findAll(pageable)
                .map(ProductResponse::from);
    }

}