package com.suarezr.products_msvc.product.services.dtos;


import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductDto {
  private String id;

  private String name;

  private String description;

  private Integer stock;

  private BigDecimal price;

  private UpdateProductDto category;
}

  