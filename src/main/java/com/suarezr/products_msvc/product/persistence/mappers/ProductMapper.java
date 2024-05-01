package com.suarezr.products_msvc.product.persistence.mappers;

import com.suarezr.products_msvc.category.persistence.mappers.CategoryMapper;
import com.suarezr.products_msvc.product.persistence.entities.ProductEntity;
import com.suarezr.products_msvc.product.services.dtos.CreateProductDto;
import com.suarezr.products_msvc.product.services.dtos.UpdateProductDto;
import com.suarezr.products_msvc.product.services.dtos.ProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CategoryMapper.class})
public interface ProductMapper {

    ////////////////Entity -> Dto////////////////
    @Mappings({

    })
    ProductDto toProductDto(ProductEntity productEntity);


    ////////////////CreateDto -> Entity////////////////
    @Mappings({
           
    })
    ProductEntity toProductEntity(CreateProductDto createProductDto);

    ////////////////UpdateDto -> Entity////////////////
    @Mappings({
          
    })
    ProductEntity toProductEntity(UpdateProductDto updateProductDto);

    ////////////Merge Entity with Dto////////////
    @Mappings({
            
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity merge(UpdateProductDto updateProductDto, @MappingTarget ProductEntity productEntity);

    /////////////list Entity-> list Dto////////////
    List<ProductDto> toProductDtoList(List<ProductEntity> productEntityList);
    
    /////////////listCreateDto -> list Entity////////////
    List<ProductEntity> toProductEntityList(List<CreateProductDto> createProductDtos);

    /////////////listUpdateDto -> list Entity////////////
    List<ProductEntity> toProductEntityListUpdate(List<UpdateProductDto> updateProductDtos);

}

  