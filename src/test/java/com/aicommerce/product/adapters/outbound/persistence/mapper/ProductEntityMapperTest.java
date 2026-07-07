package com.aicommerce.product.adapters.outbound.persistence.mapper;

import com.aicommerce.product.adapters.outbound.persistence.entity.ProductEntity;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class ProductEntityMapperTest {

    private Instant FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void shouldMapDomainToEntity() {

        UUID productId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        Product product = Product.restore(
                new ProductId(productId),
                new Sku("SKU-001"),
                new ProductName("Notebook Gamer"),
                new Description("Notebook Gamer RTX 4070"),
                new BrandId(brandId),
                new CategoryId(categoryId),
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                FIXED_INSTANT
        );

        ProductEntity entity = ProductEntityMapper.toEntity(product);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(productId);
        assertThat(entity.getSku()).isEqualTo("SKU-001");
        assertThat(entity.getName()).isEqualTo("Notebook Gamer");
        assertThat(entity.getDescription()).isEqualTo("Notebook Gamer RTX 4070");
        assertThat(entity.getBrandId()).isEqualTo(brandId);
        assertThat(entity.getCategoryId()).isEqualTo(categoryId);
        assertThat(entity.getStatus()).isEqualTo(ProductStatus.ACTIVE);
        assertThat(entity.getCreatedAt()).isEqualTo(FIXED_INSTANT);
        assertThat(entity.getUpdatedAt()).isEqualTo(FIXED_INSTANT);
    }

    @Test
    void shouldMapEntityToDomain() {

        UUID productId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        ProductEntity entity = new ProductEntity();

        entity.setId(productId);
        entity.setSku("SKU-001");
        entity.setName("Notebook Gamer");
        entity.setDescription("Notebook Gamer RTX 4070");
        entity.setBrandId(brandId);
        entity.setCategoryId(categoryId);
        entity.setStatus(ProductStatus.ACTIVE);
        entity.setCreatedAt(FIXED_INSTANT);
        entity.setUpdatedAt(FIXED_INSTANT);

        Product product = ProductEntityMapper.toDomain(entity);

        assertThat(product).isNotNull();

        assertThat(product.getId().getValue()).isEqualTo(productId);
        assertThat(product.getSku().getValue()).isEqualTo("SKU-001");
        assertThat(product.getName().getValue()).isEqualTo("Notebook Gamer");
        assertThat(product.getDescription().getValue()).isEqualTo("Notebook Gamer RTX 4070");
        assertThat(product.getBrandId().getValue()).isEqualTo(brandId);
        assertThat(product.getCategoryId().getValue()).isEqualTo(categoryId);
        assertThat(product.getStatus()).isEqualTo(ProductStatus.ACTIVE);
        assertThat(product.getCreatedAt()).isEqualTo(FIXED_INSTANT);
        assertThat(product.getUpdatedAt()).isEqualTo(FIXED_INSTANT);
    }
}