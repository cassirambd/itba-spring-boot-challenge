package com.itba.challenge.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.itba.challenge.constants.Constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
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
