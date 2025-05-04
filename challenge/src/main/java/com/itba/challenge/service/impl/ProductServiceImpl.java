package com.itba.challenge.service.impl;

import com.itba.challenge.entity.Product;
import com.itba.challenge.repository.ProductRepository;
import com.itba.challenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return List.of((Product) this.productRepository.findAll());
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productToUpdate = this.findProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productToUpdate.setProductName(product.getProductName());

        return this.productRepository.save(productToUpdate);
    }

    @Override
    public boolean deleteProductById(Long id) {
        try {
            this.productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
