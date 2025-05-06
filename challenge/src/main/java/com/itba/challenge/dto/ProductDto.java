package com.itba.challenge.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 80, message = "Product name must be less than 80 characters")
    private String productName;

    @Size(max = 50, message = "Product brand must be less than 50 characters")
    private String productBrand;

    @NotNull(message = "Product suitability is required")
    private Boolean productSuitable;

    private LocalDate productEntryDate;

    @Future(message = "Product expiration date must be in the future")
    private LocalDate productExpirationDate;
}
