package com.aicommerce.product.adapters.outbound.persistence.adapter;

import com.aicommerce.product.adapters.outbound.persistence.entity.ProductEntity;
import com.aicommerce.product.adapters.outbound.persistence.repository.SpringDataProductRepository;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductPersistenceAdapterTest {

    private Instant FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");

    @Mock
    private SpringDataProductRepository repository;

    private ProductPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ProductPersistenceAdapter(repository);
    }

    private Product createProduct() {

        UUID productId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        return Product.restore(
                new ProductId(productId),
                new Sku("SKU-001"),
                new ProductName("Notebook"),
                new Description("Notebook Gamer"),
                new BrandId(brandId),
                new CategoryId(categoryId),
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                FIXED_INSTANT
        );
    }

    private ProductEntity createProductEntity() {

        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        ProductEntity entity = new ProductEntity();

        entity.setId(UUID.randomUUID());
        entity.setSku("SKU-001");
        entity.setName("Notebook");
        entity.setDescription("Notebook Gamer");
        entity.setBrandId(brandId);
        entity.setCategoryId(categoryId);
        entity.setStatus(ProductStatus.ACTIVE);
        entity.setCreatedAt(FIXED_INSTANT);
        entity.setUpdatedAt(FIXED_INSTANT);

        return entity;
    }

    @Test
    void shouldSaveProduct() {

        Product product = createProduct();
        ProductEntity entity = createProductEntity();

        when(repository.save(any(ProductEntity.class)))
                .thenReturn(entity);

        Product saved = adapter.save(product);

        assertThat(saved).isNotNull();
        assertThat(saved.getSku().getValue()).isEqualTo("SKU-001");
        assertThat(saved.getName().getValue()).isEqualTo("Notebook");
        assertThat(saved.getDescription().getValue()).isEqualTo("Notebook Gamer");
        assertThat(saved.getStatus()).isEqualTo(ProductStatus.ACTIVE);

        verify(repository).save(any(ProductEntity.class));
    }

    @Test
    void shouldFindById() {

        ProductEntity entity = createProductEntity();

        when(repository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));

        Optional<Product> result =
                adapter.findById(new ProductId(entity.getId()));

        assertThat(result).isPresent();
        assertThat(result.get().getSku().getValue()).isEqualTo("SKU-001");

        verify(repository).findById(entity.getId());
    }

    @Test
    void shouldReturnEmptyWhenProductDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        Optional<Product> result =
                adapter.findById(new ProductId(id));

        assertThat(result).isEmpty();

        verify(repository).findById(id);
    }

    @Test
    void shouldFindBySku() {

        ProductEntity entity = createProductEntity();

        when(repository.findBySku("SKU-001"))
                .thenReturn(Optional.of(entity));

        Optional<Product> result =
                adapter.findBySku("SKU-001");

        assertThat(result).isPresent();
        assertThat(result.get().getSku().getValue())
                .isEqualTo("SKU-001");

        verify(repository).findBySku("SKU-001");
    }

    @Test
    void shouldReturnEmptyWhenSkuDoesNotExist() {

        when(repository.findBySku("SKU-001"))
                .thenReturn(Optional.empty());

        Optional<Product> result =
                adapter.findBySku("SKU-001");

        assertThat(result).isEmpty();

        verify(repository).findBySku("SKU-001");
    }

    @Test
    void shouldReturnTrueWhenSkuExists() {

        when(repository.existsBySku("SKU-001"))
                .thenReturn(true);

        boolean exists =
                adapter.existsBySku("SKU-001");

        assertThat(exists).isTrue();

        verify(repository).existsBySku("SKU-001");
    }

    @Test
    void shouldReturnFalseWhenSkuDoesNotExist() {

        when(repository.existsBySku("SKU-001"))
                .thenReturn(false);

        boolean exists =
                adapter.existsBySku("SKU-001");

        assertThat(exists).isFalse();

        verify(repository).existsBySku("SKU-001");
    }

    @Test
    void shouldFindAllProducts() {

        ProductEntity entity = createProductEntity();

        Pageable pageable = PageRequest.of(0, 10);

        Page<ProductEntity> page =
                new PageImpl<>(List.of(entity));

        when(repository.findAll(pageable))
                .thenReturn(page);

        Page<Product> result =
                adapter.findAll(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).hasSize(1);

        assertThat(result.getContent().getFirst()
                .getSku().getValue())
                .isEqualTo("SKU-001");

        verify(repository).findAll(pageable);
    }

    @Test
    void shouldDeleteProduct() {

        UUID id = UUID.randomUUID();

        adapter.deleteById(new ProductId(id));

        verify(repository).deleteById(id);
    }

}