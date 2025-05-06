package com.itba.challenge.service;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;
import com.itba.challenge.entity.Product;
import com.itba.challenge.mapper.ProductMapper;
import com.itba.challenge.repository.ProductRepository;
import com.itba.challenge.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductMapper productMapper = mock(ProductMapper.class);
    private final SmsService smsService = mock(SmsService.class);
    private final ProductServiceImpl productService = new ProductServiceImpl(productRepository, productMapper, smsService);

    @Test
    void shouldReturnAllProducts() {
        Product product = new Product();
        ProductResponse response = new ProductResponse();

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toResponse(product)).thenReturn(response);

        List<ProductResponse> products = productService.findAllProducts();

        assertEquals(1, products.size(), "Should return one product");
        verify(productRepository).findAll();
        verify(productMapper).toResponse(product);
    }

    @Test
    void shouldSaveProductAndSendSms() {
        ProductDto productDto = new ProductDto(null, "Product", "Brand", true, LocalDate.now(), LocalDate.now().plusMonths(3));
        Product productEntity = new Product();
        Product productSaved = new Product();
        ProductResponse productResponse = new ProductResponse();
        productSaved.setProductId(1L);

        when(productMapper.toEntity(productDto)).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(productSaved);
        when(productMapper.toResponse(productSaved)).thenReturn(productResponse);

        ProductResponse response = productService.saveProduct(productDto);

        assertNotNull(response, "Response should not be null");
        verify(smsService).sendSms(any());
    }
}
