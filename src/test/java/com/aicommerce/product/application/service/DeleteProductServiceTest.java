package com.aicommerce.product.application.service;

import com.aicommerce.product.application.command.DeleteProductCommand;
import com.aicommerce.product.application.port.outbound.ProductRepositoryPort;
import com.aicommerce.product.domain.exception.product.ProductNotFoundException;
import com.aicommerce.product.domain.model.Product;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private DeleteProductService service;

    private UUID productId;
    private DeleteProductCommand command;

    @BeforeEach
    void setUp() {

        productId = UUID.randomUUID();
        command = new DeleteProductCommand(productId);
    }

    @Test
    void shouldDeleteProductSuccessfully() {

        Product product = mock(Product.class);

        when(productRepositoryPort.findById(any(ProductId.class)))
                .thenReturn(Optional.of(product));

        service.execute(command);

        verify(productRepositoryPort).findById(any(ProductId.class));
        verify(productRepositoryPort).deleteById(any(ProductId.class));
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {

        when(productRepositoryPort.findById(any(ProductId.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> service.execute(command)
        );

        verify(productRepositoryPort).findById(any(ProductId.class));
        verify(productRepositoryPort, never())
                .deleteById(any(ProductId.class));
    }
}