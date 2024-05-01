package com.suarezr.products_msvc.product.persistence;

import com.suarezr.products_msvc.product.persistence.entities.ProductEntity;
import com.suarezr.products_msvc.product.persistence.mappers.ProductMapper;
import com.suarezr.products_msvc.product.persistence.repositories.ProductCrudRepository;
import com.suarezr.products_msvc.product.persistence.repositories.ProductPaginationRepository;
import com.suarezr.products_msvc.product.services.ProductServiceRepository;
import com.suarezr.products_msvc.product.services.dtos.CreateProductDto;
import com.suarezr.products_msvc.product.services.dtos.UpdateProductDto;
import com.suarezr.products_msvc.product.services.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductServiceRepository {

    private final ProductCrudRepository productCrudRepository;
    private final ProductPaginationRepository productPaginationRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(CreateProductDto createProductDto) {
        ProductEntity newProduct = this.productMapper.toProductEntity(createProductDto);
        ProductEntity responseQuery = this.productCrudRepository.save(newProduct);
        return productMapper.toProductDto(responseQuery);
    }

    @Override
    public Page<ProductDto> findAllProducts(int page, int limit, String sortBy) {
        Pageable pageRequest = PageRequest.of(page, limit, Sort.by(sortBy));
        return this.productPaginationRepository.findAll(pageRequest).map(this.productMapper::toProductDto);
    }

    @Override
    public Optional<ProductDto> findOneProduct(String id) {
        return this.productCrudRepository.findById(id).map(this.productMapper::toProductDto);
    }

    @Override
    public Optional<ProductDto> updateProduct(UpdateProductDto updateProductDto, String id) {
        ProductEntity productEntityById = this.productCrudRepository.findById(id).orElse(null);
        if(productEntityById == null) return Optional.empty();;
        ProductEntity productEntityUpdate = this.productMapper.merge(updateProductDto, productEntityById);
        ProductEntity responseQuery = this.productCrudRepository.save(productEntityUpdate);
        return Optional.of(productMapper.toProductDto(responseQuery));
    }

    @Override
    public void removeProduct(String id) {
        this.productCrudRepository.deleteById(id);
    }
}

  