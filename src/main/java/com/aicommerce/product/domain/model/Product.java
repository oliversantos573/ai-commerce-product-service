package com.aicommerce.product.domain.model;

import com.aicommerce.product.domain.event.product.ProductActivatedEvent;
import com.aicommerce.product.domain.event.product.ProductCreatedEvent;
import com.aicommerce.product.domain.event.product.ProductDeactivatedEvent;
import com.aicommerce.product.domain.event.product.ProductUpdatedEvent;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;

import java.time.Instant;
import java.util.Objects;

public class Product extends AggregateRoot<ProductId> {

    private final Sku sku;

    private ProductName name;

    private Description description;

    private BrandId brandId;

    private CategoryId categoryId;

    private ProductStatus status;

    private final Instant createdAt;

    private Instant updatedAt;

    private Product(
            ProductId id,
            Sku sku,
            ProductName name,
            Description description,
            BrandId brandId,
            CategoryId categoryId,
            Instant createdAt) {

        super(id);

        this.sku = Objects.requireNonNull(sku);
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.brandId = Objects.requireNonNull(brandId);
        this.categoryId = Objects.requireNonNull(categoryId);
        this.status = ProductStatus.DRAFT;
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = createdAt;
    }

    public static Product restore(
            ProductId id,
            Sku sku,
            ProductName name,
            Description description,
            BrandId brandId,
            CategoryId categoryId,
            ProductStatus status,
            Instant createdAt,
            Instant updatedAt) {

        Product product = new Product(
                id,
                sku,
                name,
                description,
                brandId,
                categoryId,
                createdAt
        );

        product.status = status;
        product.updatedAt = updatedAt;

        return product;
    }

    public static Product create(
            ProductId id,
            Sku sku,
            ProductName name,
            Description description,
            BrandId brandId,
            CategoryId categoryId,
            Instant now) {

        Product product = new Product(
                id,
                sku,
                name,
                description,
                brandId,
                categoryId,
                now
        );

        product.registerEvent(
                new ProductCreatedEvent(id, now)
        );

        return product;
    }

    public void update(
            ProductName name,
            Description description,
            CategoryId categoryId,
            BrandId brandId,
            Instant updatedAt) {

        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.categoryId = Objects.requireNonNull(categoryId);
        this.brandId = Objects.requireNonNull(brandId);
        this.updatedAt = Objects.requireNonNull(updatedAt);

        registerEvent(
                new ProductUpdatedEvent(getId(), updatedAt)
        );
    }

    public void activate(Instant now) {

        if (status == ProductStatus.ACTIVE) {
            return;
        }

        status = ProductStatus.ACTIVE;
        updatedAt = now;

        registerEvent(
                new ProductActivatedEvent(getId(), now)
        );
    }

    public void deactivate(Instant now) {

        if (status == ProductStatus.INACTIVE) {
            return;
        }

        status = ProductStatus.INACTIVE;
        updatedAt = now;

        registerEvent(
                new ProductDeactivatedEvent(getId(), now)
        );
    }

    public void changeName(ProductName newName, Instant now) {

        this.name = Objects.requireNonNull(newName);

        this.updatedAt = now;

        registerEvent(
                new ProductUpdatedEvent(getId(), now)
        );
    }

    public void changeDescription(Description description, Instant now) {

        this.description = Objects.requireNonNull(description);

        this.updatedAt = now;

        registerEvent(
                new ProductUpdatedEvent(getId(), now)
        );
    }

    public void changeCategory(CategoryId categoryId, Instant now) {

        this.categoryId = Objects.requireNonNull(categoryId);

        this.updatedAt = now;

        registerEvent(
                new ProductUpdatedEvent(getId(), now)
        );
    }

    public void changeBrand(BrandId brandId, Instant now) {

        this.brandId = Objects.requireNonNull(brandId);

        this.updatedAt = now;

        registerEvent(
                new ProductUpdatedEvent(getId(), now)
        );
    }

    public Sku getSku() {
        return sku;
    }

    public ProductName getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public BrandId getBrandId() {
        return brandId;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}