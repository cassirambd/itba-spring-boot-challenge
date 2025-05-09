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

import static com.itba.challenge.constants.Constants.*;

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
    public ProductResponse findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        return productMapper.toResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductDto productDto) {
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
        String productName = product.getProductName() != null ? product.getProductName() : PRODUCT_NAME_EMPTY;
        String productBrand = product.getProductBrand() != null ? product.getProductBrand() : PRODUCT_BRAND_EMPTY;
        String formattedDate = product.getProductExpirationDate() != null
                ? product.getProductExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : PRODUCT_EXPIRATION_DATE_EMPTY;

        return String.format(
                "Product %s %s added successfully! %n%nExpiration date: %s.",
                productName,
                productBrand,
                formattedDate
        );
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductDto productDto) {
        log.info("Updating product {}", productDto);
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));

        updateProductFields(productToUpdate, productDto);
        log.info("Product updated, id {}", productToUpdate.getProductId());

        return productMapper.toResponse(productRepository.save(productToUpdate));
    }

    private void updateProductFields(Product product, ProductDto dto) {
        product.setProductName(dto.getProductName());
        product.setProductBrand(dto.getProductBrand());
        product.setProductSuitable(dto.getProductSuitable());
        product.setProductExpirationDate(dto.getProductExpirationDate());
    }

    @Override
    public boolean deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, id));
        }
        productRepository.deleteById(id);
        return true;
    }
}
