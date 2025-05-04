package com.itba.challenge.dto;

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
    private String productName;
    private Boolean productSuitable;
    private String productBrand;
    private LocalDate productEntryDate;
    private LocalDate productExpirationDate;
}
