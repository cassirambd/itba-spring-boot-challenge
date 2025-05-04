package com.itba.challenge.service.impl;

import com.itba.challenge.entity.Product;
import com.itba.challenge.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
