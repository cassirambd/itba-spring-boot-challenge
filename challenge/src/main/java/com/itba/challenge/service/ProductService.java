package com.itba.challenge.service;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> findAllProducts();

    Optional<ProductResponse> findProductById(Long id);

    ProductResponse saveProduct(ProductDto productDto);

    ProductResponse updateProduct(Long id, ProductDto productDto);

    boolean deleteProductById(Long id);
}
