package com.aicommerce.product.domain.model;

import com.aicommerce.product.domain.event.DomainEvent;
import com.aicommerce.product.domain.event.product.ProductActivatedEvent;
import com.aicommerce.product.domain.event.product.ProductCreatedEvent;
import com.aicommerce.product.domain.event.product.ProductDeactivatedEvent;
import com.aicommerce.product.domain.event.product.ProductUpdatedEvent;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private ProductId productId;
    private Sku sku;
    private ProductName name;
    private Description description;
    private BrandId brandId;
    private CategoryId categoryId;
    private Instant FIXED_INSTANT;

    @BeforeEach
    void setUp() {

        productId = new ProductId(UUID.randomUUID());
        sku = new Sku("SKU-001");
        name = new ProductName("Notebook Gamer");
        description = new Description("Descrição do produto");
        brandId = new BrandId(UUID.randomUUID());
        categoryId = new CategoryId(UUID.randomUUID());
        FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");
    }

    @Test
    void shouldCreateProduct() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        assertAll(
                () -> assertEquals(productId, product.getId()),
                () -> assertEquals(sku, product.getSku()),
                () -> assertEquals(name, product.getName()),
                () -> assertEquals(description, product.getDescription()),
                () -> assertEquals(brandId, product.getBrandId()),
                () -> assertEquals(categoryId, product.getCategoryId())
        );
    }

    @Test
    void shouldInitializeStatusAsDraft() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        assertEquals(ProductStatus.DRAFT, product.getStatus());
    }

    @Test
    void shouldInitializeCreatedAtAndUpdatedAt() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        assertEquals(FIXED_INSTANT, product.getCreatedAt());
        assertEquals(FIXED_INSTANT, product.getUpdatedAt());
    }

    @Test
    void shouldRegisterProductCreatedEvent() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        List<DomainEvent> events = product.pullDomainEvents();

        assertEquals(1, events.size());
        assertInstanceOf(ProductCreatedEvent.class, events.getFirst());
    }

    @Test
    void shouldRestoreProductWithoutPublishingEvents() {

        Product product = Product.restore(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                FIXED_INSTANT
        );

        assertTrue(product.pullDomainEvents().isEmpty());
    }

    @Test
    void shouldRestoreProductKeepingState() {

        Instant updated = FIXED_INSTANT.plusSeconds(300);

        Product product = Product.restore(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                updated
        );

        assertAll(
                () -> assertEquals(ProductStatus.ACTIVE, product.getStatus()),
                () -> assertEquals(FIXED_INSTANT, product.getCreatedAt()),
                () -> assertEquals(updated, product.getUpdatedAt())
        );
    }

    @Test
    void shouldUpdateProductFields() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        ProductName newName = new ProductName("Notebook Dell");
        Description newDescription = new Description("Nova descrição");
        CategoryId newCategory = new CategoryId(UUID.randomUUID());
        BrandId newBrand = new BrandId(UUID.randomUUID());

        Instant updatedAt = FIXED_INSTANT.plusSeconds(60);

        product.update(
                newName,
                newDescription,
                newCategory,
                newBrand,
                updatedAt
        );

        assertAll(
                () -> assertEquals(newName, product.getName()),
                () -> assertEquals(newDescription, product.getDescription()),
                () -> assertEquals(newCategory, product.getCategoryId()),
                () -> assertEquals(newBrand, product.getBrandId()),
                () -> assertEquals(updatedAt, product.getUpdatedAt())
        );
    }

    @Test
    void shouldActivateProduct() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        Instant activatedAt = FIXED_INSTANT.plusSeconds(10);

        product.activate(activatedAt);

        assertEquals(ProductStatus.ACTIVE, product.getStatus());
        assertEquals(activatedAt, product.getUpdatedAt());
    }

    @Test
    void shouldRegisterActivatedEvent() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        product.pullDomainEvents();

        product.activate(FIXED_INSTANT.plusSeconds(5));

        List<DomainEvent> events = product.pullDomainEvents();

        assertEquals(1, events.size());
        assertTrue(events.getFirst() instanceof ProductActivatedEvent);
    }

    @Test
    void shouldNotRegisterActivatedEventWhenAlreadyActive() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        product.activate(FIXED_INSTANT);

        product.pullDomainEvents();

        product.activate(FIXED_INSTANT.plusSeconds(5));

        assertTrue(product.pullDomainEvents().isEmpty());
    }

    @Test
    void shouldDeactivateProduct() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        product.activate(FIXED_INSTANT);

        Instant deactivatedAt = FIXED_INSTANT.plusSeconds(20);

        product.pullDomainEvents();

        product.deactivate(deactivatedAt);

        assertEquals(ProductStatus.INACTIVE, product.getStatus());
        assertEquals(deactivatedAt, product.getUpdatedAt());
    }

    @Test
    void shouldRegisterDeactivatedEvent() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        product.activate(FIXED_INSTANT);

        product.pullDomainEvents();

        product.deactivate(FIXED_INSTANT.plusSeconds(5));

        List<DomainEvent> events = product.pullDomainEvents();

        assertEquals(1, events.size());
        assertTrue(events.getFirst() instanceof ProductDeactivatedEvent);
    }

    @Test
    void shouldNotRegisterDeactivatedEventWhenAlreadyInactive() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        product.deactivate(FIXED_INSTANT);

        product.pullDomainEvents();

        product.deactivate(FIXED_INSTANT.plusSeconds(10));

        assertTrue(product.pullDomainEvents().isEmpty());
    }

    @Test
    void shouldChangeName() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT

        );

        ProductName newName = new ProductName("MacBook Pro");

        product.pullDomainEvents();

        Instant updatedAt = FIXED_INSTANT.plusSeconds(10);

        product.changeName(newName, updatedAt);

        assertAll(
                () -> assertEquals(newName, product.getName()),
                () -> assertEquals(updatedAt, product.getUpdatedAt())
        );

        List<DomainEvent> events = product.pullDomainEvents();

        assertEquals(1, events.size());
        assertInstanceOf(ProductUpdatedEvent.class, events.getFirst());
    }

    @Test
    void shouldThrowExceptionWhenChangingNameWithNull() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        assertThrows(
                NullPointerException.class,
                () -> product.changeName(null, FIXED_INSTANT)
        );
    }

    @Test
    void shouldChangeDescription() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        Description newDescription = new Description("Descrição Atualizada");

        product.pullDomainEvents();

        Instant updatedAt = FIXED_INSTANT.plusSeconds(10);

        product.changeDescription(newDescription, updatedAt);

        assertAll(
                () -> assertEquals(newDescription, product.getDescription()),
                () -> assertEquals(updatedAt, product.getUpdatedAt())
        );

        List<DomainEvent> events = product.pullDomainEvents();

        assertEquals(1, events.size());
        assertInstanceOf(ProductUpdatedEvent.class, events.getFirst());
    }

    @Test
    void shouldThrowExceptionWhenChangingDescriptionWithNull() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        assertThrows(
                NullPointerException.class,
                () -> product.changeDescription(null, FIXED_INSTANT)
        );
    }

    @Test
    void shouldChangeCategory() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        CategoryId newCategory = new CategoryId(UUID.randomUUID());

        product.pullDomainEvents();

        Instant updatedAt = FIXED_INSTANT.plusSeconds(10);

        product.changeCategory(newCategory, updatedAt);

        assertAll(
                () -> assertEquals(newCategory, product.getCategoryId()),
                () -> assertEquals(updatedAt, product.getUpdatedAt())
        );

        List<DomainEvent> events = product.pullDomainEvents();

        assertEquals(1, events.size());
        assertInstanceOf(ProductUpdatedEvent.class, events.getFirst());
    }

    @Test
    void shouldThrowExceptionWhenChangingCategoryWithNull() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        assertThrows(
                NullPointerException.class,
                () -> product.changeCategory(null, FIXED_INSTANT)
        );
    }

    @Test
    void shouldChangeBrand() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        BrandId newBrand = new BrandId(UUID.randomUUID());

        product.pullDomainEvents();

        Instant updatedAt = FIXED_INSTANT.plusSeconds(10);

        product.changeBrand(newBrand, updatedAt);

        assertAll(
                () -> assertEquals(newBrand, product.getBrandId()),
                () -> assertEquals(updatedAt, product.getUpdatedAt())
        );

        List<DomainEvent> events = product.pullDomainEvents();

        assertEquals(1, events.size());
        assertInstanceOf(ProductUpdatedEvent.class, events.getFirst());
    }

    @Test
    void shouldThrowExceptionWhenChangingBrandWithNull() {

        Product product = Product.create(
                productId,
                sku,
                name,
                description,
                brandId,
                categoryId,
                FIXED_INSTANT
        );

        assertThrows(
                NullPointerException.class,
                () -> product.changeBrand(null, FIXED_INSTANT)
        );
    }

}