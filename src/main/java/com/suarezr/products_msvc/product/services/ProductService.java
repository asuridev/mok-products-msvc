package com.suarezr.products_msvc.product.services;

import com.suarezr.products_msvc.category.services.CategoryService;
import com.suarezr.products_msvc.category.services.dtos.ResponseCategoryDto;
import com.suarezr.products_msvc.product.services.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductServiceRepository productServiceRepository;
    private final ConverterProductDto converterProductDto;
    private final CategoryService categoryService;

    public ResponseProductDto create(CreateProductDto createProductDto){
        ResponseCategoryDto category =  this.categoryService.findOne(createProductDto.getCategory().getId());
        if(category == null) return null;
        ProductDto product = productServiceRepository.createProduct(createProductDto);
        return this.converterProductDto.toResponseProductDto(product);
    }

    public Page<ResponseProductDto> findAll(int page, int limit, String sortBy){
        Page<ProductDto> products = productServiceRepository.findAllProducts(page, limit ,sortBy);
        return  products.map(converterProductDto::toResponseProductDto);
    }

    public ResponseProductDto findOne(String id){
        ProductDto product = productServiceRepository.findOneProduct(id).orElse(null);
        return this.converterProductDto.toResponseProductDto(product);
    }

    public ResponseProductDto update(UpdateProductDto updateProductDto, String id){
        ProductDto product = productServiceRepository.updateProduct(updateProductDto, id).orElse(null);
        return this.converterProductDto.toResponseProductDto(product);
    }

    public void remove(String id){
        this.findOne(id);
        productServiceRepository.removeProduct(id);
    }
}

  