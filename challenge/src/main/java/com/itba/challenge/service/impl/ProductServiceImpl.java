package com.itba.challenge.service.impl;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;
import com.itba.challenge.entity.Product;
import com.itba.challenge.mapper.ProductMapper;
import com.itba.challenge.repository.ProductRepository;
import com.itba.challenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productMapper::toResponse).toList();
    }

    @Override
    public Optional<ProductResponse> findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toResponse(product) == null ? Optional.empty() : Optional.of(productMapper.toResponse(product));
    }

    @Override
    @Transactional
    public ProductResponse saveProduct(ProductDto productDto) {
        log.info("Saving product {}", productDto);
        Product product = productRepository.save(productMapper.toEntity(productDto));
        log.info("Product saved id {}", product.getProductId());

        return productMapper.toResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductDto productDto) {
        log.info("Updating product {}", productDto);
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        productToUpdate.setProductName(productDto.getProductName());
        productToUpdate.setProductBrand(productDto.getProductBrand());
        productToUpdate.setProductSuitable(productDto.getProductSuitable());
        productToUpdate.setProductExpirationDate(productDto.getProductExpirationDate());
        log.info("Product updated id {}", productToUpdate.getProductId());

        return productMapper.toResponse(productRepository.save(productToUpdate));
    }

    @Override
    public boolean deleteProductById(Long id) {
        try {
            if (!productRepository.existsById(id)) {
                throw new RuntimeException("Product not found");
            }
            productRepository.deleteById(id);
            return true;
        } catch (DataAccessException e) {
            throw new RuntimeException("Product not found");
        }
    }
}
