package com.itba.challenge.controller.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String productName;
    private Boolean productSuitable;
    private String productBrand;
    private String productExpirationDate;
}
