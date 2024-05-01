package com.suarezr.products_msvc.category.persistence.repositories;


import com.suarezr.products_msvc.category.persistence.entities.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CategoryPaginationRepository extends PagingAndSortingRepository <CategoryEntity,String>{


}

  