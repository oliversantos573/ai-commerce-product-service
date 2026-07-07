package com.aicommerce.product.adapters.outbound.persistence.adapter;

import com.aicommerce.product.adapters.outbound.persistence.mapper.ProductEntityMapper;
import com.aicommerce.product.adapters.outbound.persistence.repository.SpringDataProductRepository;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;

    public ProductPersistenceAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {

        return ProductEntityMapper.toDomain(
                repository.save(ProductEntityMapper.toEntity(product))
        );
    }

    @Override
    public Optional<Product> findById(ProductId id) {

        return repository.findById(id.getValue())
                .map(ProductEntityMapper::toDomain);
    }

    @Override
    public Optional<Product> findBySku(String sku) {

        return repository.findBySku(sku)
                .map(ProductEntityMapper::toDomain);
    }

    @Override
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {

        return repository
                .findAll(pageable)
                .map(ProductEntityMapper::toDomain);
    }

    @Override
    public void deleteById(ProductId id) {

        repository.deleteById(id.getValue());

    }
}