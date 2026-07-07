package com.aicommerce.product.application.mapper;

import com.aicommerce.product.application.dto.response.CreateProductResponse;
import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    Instant FIXED_INSTANT = Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void shouldMapProductToProductResponse() {



 

        Product product = Product.restore(
                new ProductId(UUID.randomUUID()),
                new Sku("SKU-001"),
                new ProductName("Notebook"),
                new Description("Notebook Gamer"),
                new BrandId(UUID.randomUUID()),
                new CategoryId(UUID.randomUUID()),
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                FIXED_INSTANT
        );

        ProductResponse response = ProductMapper.toResponse(product);

        assertNotNull(response);

        assertEquals(product.getId().getValue(), response.id());
        assertEquals(product.getSku().getValue(), response.sku());
        assertEquals(product.getName().getValue(), response.name());
        assertEquals(product.getDescription().getValue(), response.description());
        assertEquals(product.getStatus().name(), response.status());
        assertEquals(product.getCreatedAt(), response.createdAt());
        assertEquals(product.getUpdatedAt(), response.updatedAt());
    }

    @Test
    void shouldMapProductToResponse() {

        ProductId productId = new ProductId(UUID.randomUUID());

        Product product = Product.restore(
                productId,
                new Sku("SKU-001"),
                new ProductName("Notebook"),
                new Description("Notebook Gamer"),
                new BrandId(UUID.randomUUID()),
                new CategoryId(UUID.randomUUID()),
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                FIXED_INSTANT
        );

        ProductResponse response =
                ProductMapper.toResponse(product);

        assertEquals(productId.getValue(), response.id());
        assertEquals("SKU-001", response.sku());
        assertEquals("Notebook", response.name());
        assertEquals("Notebook Gamer", response.description());
        assertEquals("ACTIVE", response.status());
        assertEquals(FIXED_INSTANT, response.createdAt());
        assertEquals(FIXED_INSTANT, response.updatedAt());
    }

    @Test
    void shouldMapProductToCreateProductResponse() {


        Product product = Product.restore(
                new ProductId(UUID.randomUUID()),
                new Sku("SKU-001"),
                new ProductName("Notebook"),
                new Description("Notebook Gamer"),
                new BrandId(UUID.randomUUID()),
                new CategoryId(UUID.randomUUID()),
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                FIXED_INSTANT
        );

        CreateProductResponse response =
                ProductMapper.toCreateResponse(product);

        assertNotNull(response);
        assertNotNull(response.product());

        assertEquals(product.getId().getValue(), response.product().id());
        assertEquals(product.getSku().getValue(), response.product().sku());
        assertEquals(product.getName().getValue(), response.product().name());
        assertEquals(product.getDescription().getValue(), response.product().description());
        assertEquals(product.getStatus().name(), response.product().status());
    }

    @Test
    void shouldHavePrivateConstructor() throws Exception {

        Constructor<ProductMapper> constructor =
                ProductMapper.class.getDeclaredConstructor();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);

        ProductMapper instance = constructor.newInstance();

        assertNotNull(instance);
    }

}