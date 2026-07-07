package com.aicommerce.product.application.service;

import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.application.query.ListProductsQuery;
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
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ListProductsServiceTest {

    private ProductRepositoryPort repository;
    private ListProductsService service;
    private Instant FIXED_INSTANT;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepositoryPort.class);
        service = new ListProductsService(repository);
        FIXED_INSTANT =  Instant.parse("2020-01-01T00:00:00Z");
    }

    @Test
    void shouldListProductsSuccessfully() {

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

        Page<Product> page = new PageImpl<>(List.of(product));

        when(repository.findAll(any(Pageable.class)))
                .thenReturn(page);

        ListProductsQuery query = new ListProductsQuery(
                0,
                10,
                "name",
                "ASC"
        );

        Page<ProductResponse> response = service.execute(query);

        assertThat(response).hasSize(1);

        ProductResponse dto = response.getContent().getFirst();

        assertThat(dto.id()).isEqualTo(product.getId().getValue());
        assertThat(dto.sku()).isEqualTo(product.getSku().getValue());
        assertThat(dto.name()).isEqualTo(product.getName().getValue());
        assertThat(dto.description()).isEqualTo(product.getDescription().getValue());
        assertThat(dto.status()).isEqualTo(product.getStatus().name());

        ArgumentCaptor<Pageable> captor =
                ArgumentCaptor.forClass(Pageable.class);

        verify(repository).findAll(captor.capture());

        Pageable pageable = captor.getValue();

        assertThat(pageable.getPageNumber()).isZero();
        assertThat(pageable.getPageSize()).isEqualTo(10);

        Sort.Order order = pageable.getSort().getOrderFor("name");
        assertThat(order).isNotNull();
        assertThat(order.getDirection()).isEqualTo(Sort.Direction.ASC);
    }
}
