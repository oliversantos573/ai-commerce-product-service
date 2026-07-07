package com.aicommerce.product.adapters.inbound.rest.exception;

import com.aicommerce.product.adapters.inbound.rest.response.ErrorResponse;
import com.aicommerce.product.domain.exception.product.ProductAlreadyExistsException;
import com.aicommerce.product.domain.exception.product.ProductNotFoundException;
import com.aicommerce.product.domain.valueobject.identifier.ProductId;
import com.aicommerce.product.domain.valueobject.product.Sku;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldHandleProductNotFound() {

        ProductId productId =
                new ProductId(UUID.randomUUID());

        ProductNotFoundException exception =
                new ProductNotFoundException(productId);

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/v1/products/" + productId.getValue());

        ErrorResponse response =
                handler.handleProductNotFound(
                        exception,
                        request
                );

        assertEquals(404, response.status());
        assertEquals("Not Found", response.error());
        assertEquals(exception.getMessage(), response.message());
        assertEquals(
                "/api/v1/products/" + productId.getValue(),
                response.path()
        );

        assertNotNull(response.timestamp());
    }

    @Test
    void shouldHandleProductAlreadyExists() {

        Sku sku = new Sku("SKU-001");

        ProductAlreadyExistsException exception =
                new ProductAlreadyExistsException(sku);

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/v1/products");

        ErrorResponse response =
                handler.handleAlreadyExists(
                        exception,
                        request
                );

        assertEquals(409, response.status());
        assertEquals("Conflict", response.error());
        assertEquals(exception.getMessage(), response.message());
        assertEquals("/api/v1/products", response.path());

        assertNotNull(response.timestamp());
    }

    @Test
    void shouldHandleValidationException() {

        MethodArgumentNotValidException exception =
                mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult =
                mock(BindingResult.class);

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/v1/products");

        FieldError fieldError =
                new FieldError(
                        "createProductRequest",
                        "name",
                        "Name is required"
                );

        when(exception.getBindingResult())
                .thenReturn(bindingResult);

        when(bindingResult.getFieldErrors())
                .thenReturn(java.util.List.of(fieldError));

        ErrorResponse response =
                handler.handleValidation(
                        exception,
                        request
                );

        assertEquals(400, response.status());
        assertEquals("Bad Request", response.error());
        assertEquals("name: Name is required", response.message());
        assertEquals("/api/v1/products", response.path());

        assertNotNull(response.timestamp());
    }

    @Test
    void shouldHandleGenericException() {

        Exception exception =
                new RuntimeException("Unexpected error");

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/v1/products");

        ErrorResponse response =
                handler.handleGeneric(
                        exception,
                        request
                );

        assertEquals(500, response.status());
        assertEquals("Internal Server Error", response.error());
        assertEquals("Unexpected error", response.message());
        assertEquals("/api/v1/products", response.path());

        assertNotNull(response.timestamp());
    }
}