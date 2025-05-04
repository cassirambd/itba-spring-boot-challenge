package com.itba.challenge.service.impl;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;
import com.itba.challenge.entity.Product;
import com.itba.challenge.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAllProducts() {
        List<Product> products = this.productRepository.findAll();

        return products.stream().map(productMapper::toResponse).toList();
    }

    @Override
    public Optional<ProductResponse> findProductById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toResponse(product) == null ? Optional.empty() : Optional.of(productMapper.toResponse(product));
    }

    @Override
    public ProductResponse saveProduct(ProductDto productDto) {
        Product product = this.productRepository.save(productMapper.toEntity(productDto));

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductDto productDto) {
        Product productToUpdate = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        productToUpdate.setProductName(productDto.getProductName());
        productToUpdate.setProductBrand(productDto.getProductBrand());
        productToUpdate.setProductSuitable(productDto.getProductSuitable());
        productToUpdate.setProductExpirationDate(productDto.getProductExpirationDate());

        return productMapper.toResponse(this.productRepository.save(productToUpdate));
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
