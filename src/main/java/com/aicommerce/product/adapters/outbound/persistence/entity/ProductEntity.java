package com.aicommerce.product.adapters.outbound.persistence.entity;

import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "brand_id", nullable = false)
    private UUID brandId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    public ProductEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getBrandId() {
        return brandId;
    }

    public void setBrandId(UUID brandId) {
        this.brandId = brandId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}