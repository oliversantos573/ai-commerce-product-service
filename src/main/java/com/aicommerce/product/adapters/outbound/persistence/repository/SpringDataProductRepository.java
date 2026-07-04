package com.aicommerce.product.adapters.outbound.persistence.repository;

import com.aicommerce.product.adapters.outbound.persistence.entity.ProductEntity;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataProductRepository
        extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findBySku(String sku);

    boolean existsBySku(String sku);

    void deleteById(UUID id);

}