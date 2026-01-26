package com.orymar.shopik.http.mapper;

import com.orymar.shopik.http.dto.ProductDto;
import com.orymar.shopik.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto (ProductEntity product);
}
