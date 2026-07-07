package com.aicommerce.product.application.service;

import com.aicommerce.product.application.command.CreateProductCommand;
import com.aicommerce.product.application.dto.response.CreateProductResponse;
import com.aicommerce.product.application.port.outbound.ClockPort;
import com.aicommerce.product.application.port.outbound.DomainEventPublisherPort;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.application.port.outbound.UuidGeneratorPort;
import com.aicommerce.product.domain.event.DomainEvent;
import com.aicommerce.product.domain.event.product.ProductCreatedEvent;
import com.aicommerce.product.domain.exception.product.ProductAlreadyExistsException;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.product.Sku;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductServiceTest {

    Instant FIXED_INSTANT = Instant.parse("2020-01-01T00:00:00Z");

    @Mock
    private ProductRepositoryPort repository;

    @Mock
    private UuidGeneratorPort uuidGenerator;

    @Mock
    private ClockPort clock;

    @Mock
    private DomainEventPublisherPort eventPublisher;

    @InjectMocks
    private CreateProductService service;

    @Test
    void shouldCreateProductSuccessfully() {

        UUID productId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        CreateProductCommand command =
                new CreateProductCommand(
                        new Sku("SKU-001"),
                        "Notebook",
                        "Notebook Gamer",
                        categoryId,
                        brandId
                );

        when(repository.existsBySku("SKU-001"))
                .thenReturn(false);

        when(uuidGenerator.generate())
                .thenReturn(productId);

        when(clock.now())
                .thenReturn(FIXED_INSTANT);

        when(repository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateProductResponse response =
                service.execute(command);

        assertNotNull(response);
        assertNotNull(response.product());

        assertEquals(productId, response.product().id());
        assertEquals("SKU-001", response.product().sku());
        assertEquals("Notebook", response.product().name());
        assertEquals("Notebook Gamer", response.product().description());

        verify(repository).existsBySku("SKU-001");
        verify(uuidGenerator).generate();
        verify(clock).now();
        verify(repository).save(any(Product.class));
        verify(eventPublisher).publish(anyList());

        verifyNoMoreInteractions(
                repository,
                uuidGenerator,
                clock,
                eventPublisher
        );
    }

    @Test
    void shouldThrowProductAlreadyExistsExceptionWhenSkuAlreadyExists() {

        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        CreateProductCommand command =
                new CreateProductCommand(
                        new Sku("SKU-001"),
                        "Notebook",
                        "Notebook Gamer",
                        categoryId,
                        brandId
                );

        when(repository.existsBySku("SKU-001"))
                .thenReturn(true);

        ProductAlreadyExistsException exception =
                assertThrows(
                        ProductAlreadyExistsException.class,
                        () -> service.execute(command)
                );

        assertEquals(
                "Product with SKU 'SKU-001' already exists.",
                exception.getMessage()
        );

        verify(repository).existsBySku("SKU-001");

        verify(repository, never()).save(any(Product.class));
        verify(uuidGenerator, never()).generate();
        verify(clock, never()).now();
        verify(eventPublisher, never()).publish(anyList());

        verifyNoMoreInteractions(
                repository,
                uuidGenerator,
                clock,
                eventPublisher
        );
    }

    @Test
    void shouldCreateProductWithExpectedValues() {

        UUID productId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        CreateProductCommand command =
                new CreateProductCommand(
                        new Sku("SKU-001"),
                        "Notebook",
                        "Notebook Gamer",
                        brandId,
                        categoryId

                );

        when(repository.existsBySku("SKU-001"))
                .thenReturn(false);

        when(uuidGenerator.generate())
                .thenReturn(productId);

        when(clock.now())
                .thenReturn(FIXED_INSTANT);

        when(repository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        service.execute(command);

        ArgumentCaptor<Product> productCaptor =
                ArgumentCaptor.forClass(Product.class);

        verify(repository).save(productCaptor.capture());

        Product product = productCaptor.getValue();

        assertEquals(productId, product.getId().getValue());
        assertEquals("SKU-001", product.getSku().getValue());
        assertEquals("Notebook", product.getName().getValue());
        assertEquals("Notebook Gamer", product.getDescription().getValue());
        assertEquals(brandId, product.getBrandId().getValue());
        assertEquals(categoryId, product.getCategoryId().getValue());
        assertEquals(FIXED_INSTANT, product.getCreatedAt());
        assertEquals(FIXED_INSTANT, product.getUpdatedAt());
    }

    @Test
    void shouldPublishProductCreatedEvent() {

        UUID productId = UUID.randomUUID();
        UUID brandId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();


        CreateProductCommand command =
                new CreateProductCommand(
                        new Sku("SKU-001"),
                        "Notebook",
                        "Notebook Gamer",
                        categoryId,
                        brandId
                );

        when(repository.existsBySku("SKU-001"))
                .thenReturn(false);

        when(uuidGenerator.generate())
                .thenReturn(productId);

        when(clock.now())
                .thenReturn(FIXED_INSTANT);

        when(repository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        service.execute(command);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<DomainEvent>> eventCaptor =
                ArgumentCaptor.forClass(List.class);

        verify(eventPublisher).publish(eventCaptor.capture());

        List<DomainEvent> events = eventCaptor.getValue();

        assertNotNull(events);
        assertEquals(1, events.size());

        assertTrue(events.getFirst() instanceof ProductCreatedEvent);


        ProductCreatedEvent event =
                (ProductCreatedEvent) events.getFirst();

        assertEquals(productId, event.getProductId().getValue());
        assertEquals(FIXED_INSTANT, event.getOccurredAt());
    }

}