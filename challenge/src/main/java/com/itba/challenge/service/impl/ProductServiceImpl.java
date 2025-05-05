package com.itba.challenge.service.impl;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;
import com.itba.challenge.entity.Product;
import com.itba.challenge.exception.ProductNotFoundException;
import com.itba.challenge.mapper.ProductMapper;
import com.itba.challenge.repository.ProductRepository;
import com.itba.challenge.service.ProductService;
import com.itba.challenge.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SmsService smsService;

    @Override
    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productMapper::toResponse).toList();
    }

    @Override
    public Optional<ProductResponse> findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        ProductResponse productResponse = productMapper.toResponse(product);

        return Optional.ofNullable(productResponse);
    }

    @Override
    @Transactional
    public ProductResponse saveProduct(ProductDto productDto) {
        log.info("Saving product {}", productDto);
        Product productEntity = productMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(productEntity);
        log.info("Product saved, id {}", savedProduct.getProductId());

        String smsMessage = buildSavedProductSmsMessage(savedProduct);

        log.info("Sending SMS {}", smsMessage);
        smsService.sendSms(smsMessage);

        return productMapper.toResponse(savedProduct);
    }

    private String buildSavedProductSmsMessage(Product product) {
        String formattedDate = product.getProductExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return String.format(
                "Product %s %s added successfully! %n%nExpiration date: %s.",
                product.getProductName(),
                product.getProductBrand(),
                formattedDate
        );
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductDto productDto) {
        log.info("Updating product {}", productDto);
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

        productToUpdate.setProductName(productDto.getProductName());
        productToUpdate.setProductBrand(productDto.getProductBrand());
        productToUpdate.setProductSuitable(productDto.getProductSuitable());
        productToUpdate.setProductExpirationDate(productDto.getProductExpirationDate());
        log.info("Product updated, id {}", productToUpdate.getProductId());

        return productMapper.toResponse(productRepository.save(productToUpdate));
    }

    @Override
    public boolean deleteProductById(Long id) {
        try {
            if (!productRepository.existsById(id)) {
                throw new ProductNotFoundException("Product with id " + id + " not found");
            }
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }
}
