package com.aicommerce.product.application.service;

import com.aicommerce.product.application.command.UpdateProductCommand;
import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.port.outbound.ClockPort;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.domain.exception.product.ProductNotFoundException;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductServiceTest {



    @Mock
    private ProductRepositoryPort repository;

    @Mock
    private ClockPort clockPort;

    @InjectMocks
    private UpdateProductService service;

    private UUID id;
    private UUID brandId;
    private UUID categoryId;
    private Instant FIXED_INSTANT;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        brandId = UUID.randomUUID();
        categoryId = UUID.randomUUID();
        FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");
    }

    @Test
    void shouldUpdateProductSuccessfully() {

        Product product = Product.restore(
                new ProductId(id),
                new Sku("SKU-001"),
                new ProductName("Notebook"),
                new Description("Descrição antiga"),
                new BrandId(UUID.randomUUID()),
                new CategoryId(UUID.randomUUID()),
                ProductStatus.ACTIVE,
                FIXED_INSTANT.minusSeconds(100),
                FIXED_INSTANT.minusSeconds(100)
        );

        UpdateProductCommand command =
                new UpdateProductCommand(
                        id,
                        "Notebook Gamer",
                        "Descrição nova",
                        brandId,
                        categoryId
                );

        when(repository.findById(any()))
                .thenReturn(Optional.of(product));

        when(clockPort.now())
                .thenReturn(FIXED_INSTANT);

        when(repository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductResponse response = service.execute(command);

        ArgumentCaptor<Product> captor =
                ArgumentCaptor.forClass(Product.class);

        verify(repository).save(captor.capture());

        Product saved = captor.getValue();

        assertThat(saved.getName().getValue())
                .isEqualTo("Notebook Gamer");

        assertThat(saved.getDescription().getValue())
                .isEqualTo("Descrição nova");

        assertThat(saved.getBrandId().getValue())
                .isEqualTo(brandId);

        assertThat(saved.getCategoryId().getValue())
                .isEqualTo(categoryId);

        assertThat(saved.getUpdatedAt())
                .isEqualTo(FIXED_INSTANT);

        assertThat(response.name())
                .isEqualTo("Notebook Gamer");

        verify(repository).findById(any(ProductId.class));
        verify(repository).save(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {

        UpdateProductCommand command =
                new UpdateProductCommand(
                        id,
                        "Notebook",
                        "Descrição",
                        brandId,
                        categoryId
                );

        when(repository.findById(any()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.execute(command))
                .isInstanceOf(ProductNotFoundException.class);

        verify(repository).findById(any(ProductId.class));
        verify(repository, never()).save(any());
    }
}