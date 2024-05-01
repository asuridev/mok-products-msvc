package com.suarezr.products_msvc.category.persistence;

import com.suarezr.products_msvc.category.persistence.entities.CategoryEntity;
import com.suarezr.products_msvc.category.persistence.mappers.CategoryMapper;
import com.suarezr.products_msvc.category.persistence.repositories.CategoryCrudRepository;
import com.suarezr.products_msvc.category.persistence.repositories.CategoryPaginationRepository;
import com.suarezr.products_msvc.category.services.CategoryServiceRepository;
import com.suarezr.products_msvc.category.services.dtos.CreateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.UpdateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryServiceRepository {

    private final CategoryCrudRepository categoryCrudRepository;
    private final CategoryPaginationRepository categoryPaginationRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {
        CategoryEntity newCategory = this.categoryMapper.toCategoryEntity(createCategoryDto);
        CategoryEntity responseQuery = this.categoryCrudRepository.save(newCategory);
        return categoryMapper.toCategoryDto(responseQuery);
    }

    @Override
    public Page<CategoryDto> findAllCategorys(int page, int limit, String sortBy) {
        Pageable pageRequest = PageRequest.of(page, limit, Sort.by(sortBy));
        return this.categoryPaginationRepository.findAll(pageRequest).map(this.categoryMapper::toCategoryDto);
    }

    @Override
    public Optional<CategoryDto> findOneCategory(String id) {
        return this.categoryCrudRepository.findById(id).map(this.categoryMapper::toCategoryDto);
    }

    @Override
    public Optional<CategoryDto> updateCategory(UpdateCategoryDto updateCategoryDto, String id) {
        CategoryEntity categoryEntityById = this.categoryCrudRepository.findById(id).orElse(null);
        if(categoryEntityById == null) return Optional.empty();;
        CategoryEntity categoryEntityUpdate = this.categoryMapper.merge(updateCategoryDto, categoryEntityById);
        CategoryEntity responseQuery = this.categoryCrudRepository.save(categoryEntityUpdate);
        return Optional.of(categoryMapper.toCategoryDto(responseQuery));
    }

    @Override
    public void removeCategory(String id) {
        this.categoryCrudRepository.deleteById(id);
    }
}

  