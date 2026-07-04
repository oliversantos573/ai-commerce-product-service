package com.aicommerce.product.adapters.outbound.persistence.mapper;

import com.aicommerce.product.adapters.outbound.persistence.entity.ProductEntity;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;

public final class ProductEntityMapper {

    private ProductEntityMapper() {
    }

    public static ProductEntity toEntity(Product product) {

        ProductEntity entity = new ProductEntity();

        entity.setId(product.getId().getValue());
        entity.setSku(product.getSku().getValue());
        entity.setName(product.getName().getValue());
        entity.setDescription(product.getDescription().getValue());
        entity.setBrandId(product.getBrandId().getValue());
        entity.setCategoryId(product.getCategoryId().getValue());
        entity.setStatus(product.getStatus());
        entity.setCreatedAt(product.getCreatedAt());
        entity.setUpdatedAt(product.getUpdatedAt());

        return entity;
    }

    public static Product toDomain(ProductEntity entity) {

        return Product.restore(
                new ProductId(entity.getId()),
                new Sku(entity.getSku()),
                new ProductName(entity.getName()),
                new Description(entity.getDescription()),
                new BrandId(entity.getBrandId()),
                new CategoryId(entity.getCategoryId()),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

}