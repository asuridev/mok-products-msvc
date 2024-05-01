package com.suarezr.products_msvc.category.services;

import com.suarezr.products_msvc.category.services.dtos.CreateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.UpdateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.CategoryDto;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface CategoryServiceRepository {

    public CategoryDto createCategory(CreateCategoryDto createCategoryDto);

    public Page<CategoryDto> findAllCategorys(int page, int limit, String sortBy);

    public Optional<CategoryDto> findOneCategory(String id);

    public Optional<CategoryDto> updateCategory(UpdateCategoryDto updateCategoryDto, String id);

    public void removeCategory(String id);
}

  