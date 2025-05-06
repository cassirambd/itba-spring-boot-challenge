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
    public static final String NAME_REQUIRED = "Product name is required";
    public static final String NAME_SIZE = "Product name must be less than 80 characters";
    public static final String BRAND_REQUIRED = "Product brand is required";
    public static final String BRAND_SIZE = "Product brand must be less than 50 characters";
    public static final String SUITABLE_REQUIRED = "Product suitability is required";
    public static final String EXPIRATION_FUTURE = "Product expiration date must be in the future";

    private Long productId;

    @NotBlank(message = NAME_REQUIRED)
    @Size(max = 80, message = NAME_SIZE)
    private String productName;

    @NotBlank(message = BRAND_REQUIRED)
    @Size(max = 50, message = BRAND_SIZE)
    private String productBrand;

    @NotNull(message = SUITABLE_REQUIRED)
    private Boolean productSuitable;

    private LocalDate productEntryDate;

    @Future(message = EXPIRATION_FUTURE)
    private LocalDate productExpirationDate;
}
