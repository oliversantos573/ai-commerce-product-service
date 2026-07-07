package com.aicommerce.product.application.service;

import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.application.query.GetProductByIdQuery;
import com.aicommerce.product.domain.exception.product.ProductNotFoundException;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.shared.enumaration.ProductStatus;
import com.aicommerce.product.domain.valueobject.identifier.BrandId;
import com.aicommerce.product.domain.valueobject.identifier.CategoryId;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Description;
import com.aicommerce.product.domain.valueobject.product.ProductName;
import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductByIdServiceTest {

    Instant FIXED_INSTANT = Instant.parse("2020-01-01T00:00:00Z");

    @Mock
    private ProductRepositoryPort repository;

    @InjectMocks
    private GetProductByIdService service;

    @Test
    void shouldReturnProductWhenProductExists() {

        UUID id = UUID.randomUUID();

        Product product = Product.restore(
                new ProductId(id),
                new Sku("SKU-001"),
                new ProductName("Notebook"),
                new Description("Notebook Gamer"),
                new BrandId(UUID.randomUUID()),
                new CategoryId(UUID.randomUUID()),
                ProductStatus.ACTIVE,
                FIXED_INSTANT,
                FIXED_INSTANT
        );

        when(repository.findById(any(ProductId.class)))
                .thenReturn(Optional.of(product));

        ProductResponse response =
                service.execute(
                        new GetProductByIdQuery(id)
                );

        assertNotNull(response);

        assertEquals(id, response.id());
        assertEquals("SKU-001", response.sku());
        assertEquals("Notebook", response.name());
        assertEquals("Notebook Gamer", response.description());
        assertEquals("ACTIVE", response.status());

        verify(repository).findById(any(ProductId.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenProductDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(repository.findById(any(ProductId.class)))
                .thenReturn(Optional.empty());

        ProductNotFoundException exception =
                assertThrows(
                        ProductNotFoundException.class,
                        () -> service.execute(
                                new GetProductByIdQuery(id)
                        )
                );

        assertEquals(
                "Product not found. Id: " + id,
                exception.getMessage()
        );

        verify(repository).findById(any(ProductId.class));
        verifyNoMoreInteractions(repository);
    }

}

