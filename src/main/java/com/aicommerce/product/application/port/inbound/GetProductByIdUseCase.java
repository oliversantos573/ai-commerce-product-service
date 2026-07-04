package com.aicommerce.product.application.port.inbound;

import com.aicommerce.product.application.dto.response.ProductResponse;
import com.aicommerce.product.application.query.GetProductByIdQuery;

public interface GetProductByIdUseCase {

    ProductResponse execute(GetProductByIdQuery query);

}