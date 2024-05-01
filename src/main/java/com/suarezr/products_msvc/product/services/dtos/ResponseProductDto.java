package com.suarezr.products_msvc.product.services.dtos;

import com.suarezr.products_msvc.category.services.dtos.ResponseCategoryDto;
import lombok.*;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProductDto {
  private String id;

  private String name;

  private String description;

  private Integer stock;

  private BigDecimal price;

  private ResponseCategoryDto category;
}

  