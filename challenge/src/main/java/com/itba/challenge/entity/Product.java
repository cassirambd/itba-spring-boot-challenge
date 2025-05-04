package com.itba.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NM_PRODUCT_ID", unique = true, nullable = false)
    private Long productId;

    @Column(name = "VA_PRODUCT_NAME", nullable = false, length = 80)
    private String productName;

    @Column(name = "BO_PRODUCT_SUITABLE")
    private Boolean productSuitable;

    @Column(name = "VA_PRODUCT_BRAND", length = 50)
    private String productBrand;

    @Column(name = "DA_PRODUCT_ENTRY_DATE")
    private LocalDate productEntryDate;

    @Column(name = "DA_PRODUCT_EXPIRATION_DATE")
    private LocalDate productExpirationDate;

    @PrePersist
    public void prePersist() {
        if (this.productEntryDate == null) {
            this.productEntryDate = LocalDate.now();
        }
    }
}
