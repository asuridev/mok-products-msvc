package com.suarezr.products_msvc.product.persistence.repositories;


import com.suarezr.products_msvc.product.persistence.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductCrudRepository extends CrudRepository <ProductEntity,String>{


}

  