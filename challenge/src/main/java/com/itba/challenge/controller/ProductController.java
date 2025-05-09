package com.itba.challenge.controller;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;
import com.itba.challenge.exception.ProductNotFoundException;
import com.itba.challenge.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable final Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody final ProductDto productDto) {
        ProductResponse createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping()
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody final ProductDto productDto) {
        ProductResponse updatedProduct = productService.updateProduct(productDto.getProductId(), productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable final Long id) {
        boolean deleted = productService.deleteProductById(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
