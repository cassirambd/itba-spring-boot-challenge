package com.itba.challenge.controller;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.itba.challenge.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private final ProductService productService = mock(ProductService.class);
    private final ProductController controller = new ProductController(productService);
    private final ProductResponse product = new ProductResponse(1L, "Product", "Brand", true, "2025-08-01");
    private final HttpStatus expectedStatus = HttpStatus.OK;

    @Test
    void shouldReturnAllProducts() {
        List<ProductResponse> expectedProducts = List.of(product);

        when(productService.findAllProducts()).thenReturn(expectedProducts);

        ResponseEntity<List<ProductResponse>> response = controller.findAllProducts();

        assertEquals(expectedStatus, response.getStatusCode(), String.format(STATUS_MSG, expectedStatus.value(), response.getStatusCode().value()));
        assertNotNull(response.getBody(), BODY_NULL_MSG);
        assertEquals(expectedProducts.size(), response.getBody().size(), String.format(BODY_SIZE_MSG, expectedProducts.size(), response.getBody().size()));
        verify(productService).findAllProducts();
    }

    @Test
    void shouldReturnProductById() {
        when(productService.findProductById(product.getProductId())).thenReturn((product));

        ResponseEntity<ProductResponse> response = controller.findProductById(product.getProductId());

        assertEquals(expectedStatus, response.getStatusCode(), String.format(STATUS_MSG, expectedStatus.value(), response.getStatusCode().value()));
        assertNotNull(response.getBody(), BODY_NULL_MSG);
        assertEquals(product.getProductName(), response.getBody().getProductName(), String.format(BODY_NAME_MSG, product.getProductName(), response.getBody().getProductName()));
        verify(productService).findProductById(product.getProductId());
    }
}
