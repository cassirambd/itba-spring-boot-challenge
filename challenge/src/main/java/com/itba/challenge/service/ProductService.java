package com.itba.challenge.service;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(Long id);

    ProductResponse createProduct(ProductDto productDto);

    ProductResponse updateProduct(Long id, ProductDto productDto);

    boolean deleteProductById(Long id);
}
