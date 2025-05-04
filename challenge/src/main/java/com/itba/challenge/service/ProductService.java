package com.itba.challenge.service;

import com.itba.challenge.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Optional<Product> findProductById(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Long id, Product product);

    boolean deleteProductById(Long id);
}
