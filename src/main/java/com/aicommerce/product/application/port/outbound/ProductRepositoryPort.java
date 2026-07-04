package com.aicommerce.product.application.port.outbound;

import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(ProductId id);

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);

    Page<Product> findAll(Pageable pageable);

    void deleteById(ProductId id);

}