package com.itba.challenge.mapper;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.dto.ProductDto;
import com.itba.challenge.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-04T22:33:16-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.productId( product.getProductId() );
        productDto.productName( product.getProductName() );
        productDto.productSuitable( product.getProductSuitable() );
        productDto.productBrand( product.getProductBrand() );
        productDto.productEntryDate( product.getProductEntryDate() );
        productDto.productExpirationDate( product.getProductExpirationDate() );

        return productDto.build();
    }

    @Override
    public Product toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productId( dto.getProductId() );
        product.productName( dto.getProductName() );
        product.productSuitable( dto.getProductSuitable() );
        product.productBrand( dto.getProductBrand() );
        product.productEntryDate( dto.getProductEntryDate() );
        product.productExpirationDate( dto.getProductExpirationDate() );

        return product.build();
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.productExpirationDate( ProductMapper.formatDate( product.getProductExpirationDate() ) );
        productResponse.productId( product.getProductId() );
        productResponse.productName( product.getProductName() );
        productResponse.productSuitable( product.getProductSuitable() );
        productResponse.productBrand( product.getProductBrand() );

        return productResponse.build();
    }
}
