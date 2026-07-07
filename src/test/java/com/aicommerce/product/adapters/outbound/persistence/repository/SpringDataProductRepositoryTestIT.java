package com.aicommerce.product.adapters.outbound.persistence.repository;

import com.aicommerce.product.adapters.outbound.persistence.entity.ProductEntity;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class SpringDataProductRepositoryTestIT {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("productdb")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        registry.add("spring.flyway.enabled", () -> true);
    }

    @Autowired
    private SpringDataProductRepository repository;

    private ProductEntity createProductEntity(String sku) {

        ProductEntity entity = new ProductEntity();

        entity.setId(UUID.randomUUID());
        entity.setSku(sku);
        entity.setName("Notebook");
        entity.setDescription("Notebook Gamer");
        entity.setBrandId(UUID.randomUUID());
        entity.setCategoryId(UUID.randomUUID());
        entity.setStatus(ProductStatus.ACTIVE);
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());

        return entity;
    }

    @Test
    void shouldSaveProduct() {

        ProductEntity saved = repository.save(createProductEntity("SKU-001"));

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("SKU-001", saved.getSku());
    }

    @Test
    void shouldFindProductBySku() {

        repository.save(createProductEntity("SKU-001"));

        var result = repository.findBySku("SKU-001");

        assertTrue(result.isPresent());
        assertEquals("SKU-001", result.get().getSku());
        assertEquals("Notebook", result.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenSkuDoesNotExist() {

        var result = repository.findBySku("SKU-NOT-EXISTS");

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenSkuExists() {

        repository.save(createProductEntity("SKU-001"));

        assertTrue(repository.existsBySku("SKU-001"));
    }

    @Test
    void shouldReturnFalseWhenSkuDoesNotExist() {

        assertFalse(repository.existsBySku("SKU-001"));
    }

    @Test
    void shouldDeleteProductById() {

        ProductEntity saved = repository.save(createProductEntity("SKU-001"));

        repository.deleteById(saved.getId());

        assertFalse(repository.findById(saved.getId()).isPresent());
    }

    @Test
    void shouldFindProductById() {

        ProductEntity saved = repository.save(createProductEntity("SKU-001"));

        var result = repository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved.getId(), result.get().getId());
        assertEquals("SKU-001", result.get().getSku());
    }

    @Test
    void shouldFindAllProducts() {

        repository.save(createProductEntity("SKU-001"));
        repository.save(createProductEntity("SKU-002"));

        Page<ProductEntity> page =
                repository.findAll(PageRequest.of(0, 10));

        assertNotNull(page);
        assertEquals(2, page.getTotalElements());
        assertEquals(2, page.getContent().size());
    }

}