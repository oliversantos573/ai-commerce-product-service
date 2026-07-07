package com.aicommerce.product.adapters.inbound.rest;

import com.aicommerce.product.adapters.inbound.config.SecurityConfig;
import com.aicommerce.product.adapters.inbound.rest.request.CreateProductRequest;
import com.aicommerce.product.application.command.CreateProductCommand;
import com.aicommerce.product.application.command.DeleteProductCommand;
import com.aicommerce.product.application.dto.response.CreateProductResponse;
import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.port.inbound.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.Instant;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.aicommerce.product.application.query.GetProductByIdQuery;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;
import com.aicommerce.product.adapters.inbound.rest.request.UpdateProductRequest;
import com.aicommerce.product.application.command.UpdateProductCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@WebMvcTest(ProductController.class)
@Import(SecurityConfig.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateProductUseCase createProductUseCase;

    @MockitoBean
    private GetProductByIdUseCase getProductByIdUseCase;

    @MockitoBean
    private ListProductsUseCase listProductsUseCase;

    @MockitoBean
    private UpdateProductUseCase updateProductUseCase;

    @MockitoBean
    private DeleteProductUseCase deleteProductUseCase;

    private Instant FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void shouldCreateProduct() throws Exception {

        UUID productId = UUID.randomUUID();
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

        ProductResponse response =
                new ProductResponse(
                        productId,
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        "DRAFT",
                        FIXED_INSTANT,
                        FIXED_INSTANT
                );

        CreateProductResponse createResponse =
                new CreateProductResponse(response);

        when(createProductUseCase.execute(any(CreateProductCommand.class)))
                .thenReturn(createResponse);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.product.id").value(productId.toString()))
                .andExpect(jsonPath("$.product.sku").value("SKU-001"))
                .andExpect(jsonPath("$.product.name").value("Notebook"))
                .andExpect(jsonPath("$.product.description").value("Notebook Gamer"))
                .andExpect(jsonPath("$.product.status").value("DRAFT"));

        verify(createProductUseCase)
                .execute(any(CreateProductCommand.class));
    }

    @Test
    void shouldFindProductById() throws Exception {

        UUID productId = UUID.randomUUID();


        ProductResponse response =
                new ProductResponse(
                        productId,
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        "DRAFT",
                        FIXED_INSTANT,
                        FIXED_INSTANT
                );

        when(getProductByIdUseCase.execute(any(GetProductByIdQuery.class)))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId.toString()))
                .andExpect(jsonPath("$.sku").value("SKU-001"))
                .andExpect(jsonPath("$.name").value("Notebook"))
                .andExpect(jsonPath("$.description").value("Notebook Gamer"))
                .andExpect(jsonPath("$.status").value("DRAFT"));

        verify(getProductByIdUseCase)
                .execute(any(GetProductByIdQuery.class));
    }

    @Test
    void shouldListProducts() throws Exception {

        UUID productId = UUID.randomUUID();


        ProductResponse response =
                new ProductResponse(
                        productId,
                        "SKU-001",
                        "Notebook",
                        "Notebook Gamer",
                        "DRAFT",
                        FIXED_INSTANT,
                        FIXED_INSTANT
                );

        Page<ProductResponse> page =
                new PageImpl<>(List.of(response));

        when(listProductsUseCase.execute(any()))
                .thenReturn(page);

        mockMvc.perform(get("/api/v1/products")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "createdAt")
                        .param("direction", "DESC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(productId.toString()))
                .andExpect(jsonPath("$.content[0].sku").value("SKU-001"))
                .andExpect(jsonPath("$.content[0].name").value("Notebook"))
                .andExpect(jsonPath("$.content[0].description").value("Notebook Gamer"))
                .andExpect(jsonPath("$.content[0].status").value("DRAFT"));

        verify(listProductsUseCase)
                .execute(any());
    }

    @Test
    void shouldUpdateProduct() throws Exception {

        UUID productId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        UpdateProductRequest request =
                new UpdateProductRequest(
                        "Notebook Atualizado",
                        "Notebook Gamer RTX 5090",
                        categoryId,
                        brandId
                );

        ProductResponse response =
                new ProductResponse(
                        productId,
                        "SKU-001",
                        "Notebook Atualizado",
                        "Notebook Gamer RTX 5090",
                        "DRAFT",
                        FIXED_INSTANT,
                        FIXED_INSTANT
                );

        when(updateProductUseCase.execute(any(UpdateProductCommand.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId.toString()))
                .andExpect(jsonPath("$.sku").value("SKU-001"))
                .andExpect(jsonPath("$.name").value("Notebook Atualizado"))
                .andExpect(jsonPath("$.description").value("Notebook Gamer RTX 5090"))
                .andExpect(jsonPath("$.status").value("DRAFT"));

        verify(updateProductUseCase)
                .execute(any(UpdateProductCommand.class));
    }

    @Test
    void shouldDeleteProduct() throws Exception {

        UUID productId = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/products/{id}", productId))
                .andExpect(status().isNoContent());

        verify(deleteProductUseCase)
                .execute(any(DeleteProductCommand.class));
    }

}