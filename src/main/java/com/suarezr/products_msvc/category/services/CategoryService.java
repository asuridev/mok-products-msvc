package com.suarezr.products_msvc.category.services;

import com.suarezr.products_msvc.common.exceptions.NotFoundException;
import com.suarezr.products_msvc.category.services.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryServiceRepository categoryServiceRepository;
    private final ConverterCategoryDto converterCategoryDto;

    public ResponseCategoryDto create(CreateCategoryDto createCategoryDto){
        CategoryDto category = categoryServiceRepository.createCategory(createCategoryDto);
        return this.converterCategoryDto.toResponseCategoryDto(category);
    }

    public Page<ResponseCategoryDto> findAll(int page, int limit, String sortBy){
        Page<CategoryDto> categorys = categoryServiceRepository.findAllCategorys(page, limit ,sortBy);
        return  categorys.map(converterCategoryDto::toResponseCategoryDto);
    }

    public ResponseCategoryDto findOne(String id){
        CategoryDto category = categoryServiceRepository.findOneCategory(id).orElse(null);
        if(category == null) return null;
        return this.converterCategoryDto.toResponseCategoryDto(category);
    }

    public ResponseCategoryDto update(UpdateCategoryDto updateCategoryDto, String id){
        CategoryDto category = categoryServiceRepository.updateCategory(updateCategoryDto, id).orElse(null);
        if(category == null) return null;
        return this.converterCategoryDto.toResponseCategoryDto(category);
    }

    public void remove(String id){
        this.findOne(id);
        categoryServiceRepository.removeCategory(id);
    }
}

  