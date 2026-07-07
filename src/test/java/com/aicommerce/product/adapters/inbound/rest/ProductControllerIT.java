package com.aicommerce.product.adapters.inbound.rest;

import com.aicommerce.product.adapters.inbound.rest.request.CreateProductRequest;
import com.aicommerce.product.adapters.inbound.rest.request.UpdateProductRequest;
import com.aicommerce.product.adapters.outbound.persistence.entity.ProductEntity;
import com.aicommerce.product.adapters.outbound.persistence.repository.SpringDataProductRepository;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.time.Instant;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductControllerIT {

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
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringDataProductRepository productRepository;

    private Instant FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");

    @BeforeEach
    void setup() {
        productRepository   .deleteAll();
    }

    @Test
    void shouldCreateProduct() throws Exception {

        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        CreateProductRequest request =
                new CreateProductRequest(
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        brandId,
                        categoryId
                );

        mockMvc.perform(
                        post("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())

                // Response
                .andExpect(jsonPath("$.product.id").exists())
                .andExpect(jsonPath("$.product.sku").value("SKU-001"))
                .andExpect(jsonPath("$.product.name").value("Notebook"))
                .andExpect(jsonPath("$.product.description").value("Notebook Gamer"))
                .andExpect(jsonPath("$.product.status").value("DRAFT"))
                .andExpect(jsonPath("$.product.createdAt").exists())
                .andExpect(jsonPath("$.product.updatedAt").exists());

        ProductEntity saved =
                productRepository.findBySku("SKU-001")
                        .orElseThrow();

        assertThat(saved.getSku()).isEqualTo("SKU-001");
        assertThat(saved.getName()).isEqualTo("Notebook");
        assertThat(saved.getDescription()).isEqualTo("Notebook Gamer");
        assertThat(saved.getBrandId()).isEqualTo(brandId);
        assertThat(saved.getCategoryId()).isEqualTo(categoryId);
        assertThat(saved.getStatus()).isEqualTo(ProductStatus.DRAFT);
    }

    @Test
    void shouldGetProductById() throws Exception {

        UUID id = UUID.randomUUID();

        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setSku("SKU-GET-001");
        entity.setName("Notebook");
        entity.setDescription("Notebook Gamer");
        entity.setBrandId(UUID.randomUUID());
        entity.setCategoryId(UUID.randomUUID());
        entity.setStatus(ProductStatus.DRAFT);
        entity.setCreatedAt(FIXED_INSTANT);
        entity.setUpdatedAt(FIXED_INSTANT);

        productRepository.save(entity);

        mockMvc.perform(get("/api/v1/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.sku").value("SKU-GET-001"))
                .andExpect(jsonPath("$.name").value("Notebook"))
                .andExpect(jsonPath("$.description").value("Notebook Gamer"))
                .andExpect(jsonPath("$.status").value("DRAFT"));
    }

    @Test
    void shouldListProducts() throws Exception {

        ProductEntity product1 = new ProductEntity();
        product1.setId(UUID.randomUUID());
        product1.setSku("SKU-001");
        product1.setName("Notebook");
        product1.setDescription("Notebook Gamer");
        product1.setBrandId(UUID.randomUUID());
        product1.setCategoryId(UUID.randomUUID());
        product1.setStatus(ProductStatus.DRAFT);
        product1.setCreatedAt(FIXED_INSTANT);
        product1.setUpdatedAt(FIXED_INSTANT);

        ProductEntity product2 = new ProductEntity();
        product2.setId(UUID.randomUUID());
        product2.setSku("SKU-002");
        product2.setName("Mouse");
        product2.setDescription("Mouse Gamer");
        product2.setBrandId(UUID.randomUUID());
        product2.setCategoryId(UUID.randomUUID());
        product2.setStatus(ProductStatus.DRAFT);
        product2.setCreatedAt(FIXED_INSTANT);
        product2.setUpdatedAt(FIXED_INSTANT);

        productRepository.save(product1);
        productRepository.save(product2);

        mockMvc.perform(
                        get("/api/v1/products")
                                .param("page", "0")
                                .param("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].sku").exists())
                .andExpect(jsonPath("$.content[1].sku").exists());
    }

    @Test
    void shouldUpdateProduct() throws Exception {

        UUID id = UUID.randomUUID();

        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setSku("SKU-001");
        entity.setName("Notebook");
        entity.setDescription("Notebook Gamer");
        entity.setBrandId(UUID.randomUUID());
        entity.setCategoryId(UUID.randomUUID());
        entity.setStatus(ProductStatus.DRAFT);
        entity.setCreatedAt(FIXED_INSTANT);
        entity.setUpdatedAt(FIXED_INSTANT);

        productRepository.save(entity);

        UpdateProductRequest request = new UpdateProductRequest(
                "Notebook Dell",
                "Notebook Gamer RTX 5070",
                 UUID.randomUUID(),
                 UUID.randomUUID()

        );

        mockMvc.perform(
                        put("/api/v1/products/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Notebook Dell"))
                .andExpect(jsonPath("$.description").value("Notebook Gamer RTX 5070"));
    }

    @Test
    void shouldDeleteProduct() throws Exception {

        UUID id = UUID.randomUUID();

        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setSku("SKU-DELETE");
        entity.setName("Notebook");
        entity.setDescription("Notebook Gamer");
        entity.setBrandId(UUID.randomUUID());
        entity.setCategoryId(UUID.randomUUID());
        entity.setStatus(ProductStatus.DRAFT);
        entity.setCreatedAt(FIXED_INSTANT);
        entity.setUpdatedAt(FIXED_INSTANT);

        productRepository.save(entity);

        mockMvc.perform(delete("/api/v1/products/{id}", id))
                .andExpect(status().isNoContent());

        assertThat(productRepository.findById(id)).isEmpty();
    }

    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {

        UUID id = UUID.randomUUID();

        mockMvc.perform(get("/api/v1/products/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenRequestIsInvalid() throws Exception {

        String json = """
        {
            "sku": "",
            "name": "",
            "description": "",
            "brandId": null,
            "categoryId": null
        }
        """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}