package com.suarezr.products_msvc.product.services.dtos;

import com.suarezr.products_msvc.category.services.dtos.CreateCategoryDto;
import lombok.*;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDto {
  private String id;

  private String name;

  private String description;

  private Integer stock;

  private BigDecimal price;

  private CreateCategoryDto category;
     
}

  