package com.itba.challenge.repository;

import com.itba.challenge.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void shouldSaveAndGetProduct() {
        Product product = createTestProduct();
        Product savedProduct = repository.save(product);

        Optional<Product> foundProduct = repository.findById(savedProduct.getProductId());

        assertTrue(foundProduct.isPresent(), "Product should be found");
        assertEquals(product.getProductName(), foundProduct.get().getProductName(), "Product names should be equal");
    }

    @Test
    void shouldReturnFalseIfProductDoesNotExist() {
        boolean exists = repository.existsById(999L);
        assertFalse(exists, "Product with id 999 should not exist");
    }

    private Product createTestProduct() {
        return new Product(
                null,
                "Test",
                "Brand",
                true,
                LocalDate.now(),
                LocalDate.now().plusMonths(3)
        );
    }
}
