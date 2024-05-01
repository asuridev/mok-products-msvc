package com.suarezr.products_msvc.category.persistence.mappers;

import com.suarezr.products_msvc.category.persistence.entities.CategoryEntity;
import com.suarezr.products_msvc.category.services.dtos.CreateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.UpdateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.CategoryDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    ////////////////Entity -> Dto////////////////
    @Mappings({

    })
    CategoryDto toCategoryDto(CategoryEntity categoryEntity);


    ////////////////CreateDto -> Entity////////////////
    @Mappings({
           
    })
    CategoryEntity toCategoryEntity(CreateCategoryDto createCategoryDto);

    ////////////////UpdateDto -> Entity////////////////
    @Mappings({
          
    })
    CategoryEntity toCategoryEntity(UpdateCategoryDto updateCategoryDto);

    ////////////Merge Entity with Dto////////////
    @Mappings({
            
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryEntity merge(UpdateCategoryDto updateCategoryDto, @MappingTarget CategoryEntity categoryEntity);

    /////////////list Entity-> list Dto////////////
    List<CategoryDto> toCategoryDtoList(List<CategoryEntity> categoryEntityList);
    
    /////////////listCreateDto -> list Entity////////////
    List<CategoryEntity> toCategoryEntityList(List<CreateCategoryDto> createCategoryDtos);

    /////////////listUpdateDto -> list Entity////////////
    List<CategoryEntity> toCategoryEntityListUpdate(List<UpdateCategoryDto> updateCategoryDtos);

}

  