package com.suarezr.products_msvc.category.services.dtos;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryDto {

  private String id;

  private String name;
}

  