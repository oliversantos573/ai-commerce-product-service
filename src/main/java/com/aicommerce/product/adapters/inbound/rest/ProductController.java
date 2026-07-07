package com.aicommerce.product.adapters.inbound.rest;

import com.aicommerce.product.adapters.inbound.rest.mapper.ProductRequestMapper;
import com.aicommerce.product.adapters.inbound.rest.request.CreateProductRequest;
import com.aicommerce.product.adapters.inbound.rest.request.UpdateProductRequest;
import com.aicommerce.product.application.command.DeleteProductCommand;
import com.aicommerce.product.application.dto.response.CreateProductResponse;
import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.port.inbound.*;
import com.aicommerce.product.application.query.GetProductByIdQuery;
import com.aicommerce.product.application.query.ListProductsQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Product Management API")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(
            CreateProductUseCase createProductUseCase,
            GetProductByIdUseCase getProductByIdUseCase,
            ListProductsUseCase listProductsUseCase,
            UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase) {

        this.createProductUseCase = createProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product")
    public CreateProductResponse create(
            @Valid @RequestBody CreateProductRequest request) {

        return createProductUseCase.execute(
                ProductRequestMapper.toCommand(request)
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get product by id")
    public ProductResponse findById(
            @PathVariable UUID id) {

        return getProductByIdUseCase.execute(
                new GetProductByIdQuery(id)
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List products")
    public Page<ProductResponse> findAll(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "createdAt") String sortBy,

            @RequestParam(defaultValue = "DESC") String direction) {

        return listProductsUseCase.execute(
                new ListProductsQuery(
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update product")
    public ProductResponse update(

            @PathVariable UUID id,

            @Valid
            @RequestBody UpdateProductRequest request

    ) {

        return updateProductUseCase.execute(
                ProductRequestMapper.toCommand(id, request)
        );

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {

        deleteProductUseCase.execute(
                new DeleteProductCommand(id)
        );

    }

}