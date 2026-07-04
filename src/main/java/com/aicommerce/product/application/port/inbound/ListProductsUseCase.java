package com.aicommerce.product.application.port.inbound;

import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.query.ListProductsQuery;
import org.springframework.data.domain.Page;

public interface ListProductsUseCase {

    Page<ProductResponse> execute(ListProductsQuery query);

}