package com.suarezr.products_msvc.category.services.dtos;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConverterCategoryDto {

    @Mappings({

    })
    ResponseCategoryDto toResponseCategoryDto(CategoryDto categoryDto);

    List<ResponseCategoryDto> toResponseCategoryDtoList(List<CategoryDto> categoryDtoList);
}
  