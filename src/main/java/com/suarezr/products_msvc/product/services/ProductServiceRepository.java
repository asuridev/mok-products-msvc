package com.suarezr.products_msvc.product.services;

import com.suarezr.products_msvc.product.services.dtos.CreateProductDto;
import com.suarezr.products_msvc.product.services.dtos.UpdateProductDto;
import com.suarezr.products_msvc.product.services.dtos.ProductDto;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface ProductServiceRepository {

    public ProductDto createProduct(CreateProductDto createProductDto);

    public Page<ProductDto> findAllProducts(int page, int limit, String sortBy);

    public Optional<ProductDto> findOneProduct(String id);

    public Optional<ProductDto> updateProduct(UpdateProductDto updateProductDto, String id);

    public void removeProduct(String id);
}

  