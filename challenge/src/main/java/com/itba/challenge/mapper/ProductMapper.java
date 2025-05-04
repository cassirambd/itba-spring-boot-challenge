package com.itba.challenge.mapper;

import com.itba.challenge.controller.response.ProductResponse;
import com.itba.challenge.entity.Product;
import com.itba.challenge.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);

    Product toEntity(ProductDto dto);

    @Mapping(target = "productExpirationDate", source = "productExpirationDate", qualifiedByName = "formatDate")
    ProductResponse toResponse(Product product);

    @Named("formatDate")
    static String formatDate(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}