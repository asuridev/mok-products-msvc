package com.suarezr.products_msvc.category.controller;

import com.suarezr.products_msvc.category.services.CategoryService;
import com.suarezr.products_msvc.category.services.dtos.CreateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.UpdateCategoryDto;
import com.suarezr.products_msvc.category.services.dtos.ResponseCategoryDto;
import com.suarezr.products_msvc.common.dtos.PaginationDto;
import com.suarezr.products_msvc.common.dtos.PathParamDto;
import com.suarezr.products_msvc.common.nats.NatsClient;
import com.suarezr.products_msvc.common.nats.StatusCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final NatsClient natsClient;

    @PostConstruct
    void start(){

        this.natsClient.createDispatcher().subscribe("category.create", (msg)->{
            CreateCategoryDto createCategoryDto = this.natsClient.toJava(msg,CreateCategoryDto.class);
            ResponseCategoryDto newCategory = this.categoryService.create(createCategoryDto);
            ResponseCategoryDto responseCategoryDto =  this.categoryService.findOne(newCategory.getId());
            this.natsClient.reply(msg, responseCategoryDto);
        });

        this.natsClient.createDispatcher().subscribe("category.read", (msg)->{
            PaginationDto paginationDto = this.natsClient.toJava(msg, PaginationDto.class);
            Page<ResponseCategoryDto> pageResponse = this.categoryService.findAll(paginationDto.getPage(), paginationDto.getLimit(),paginationDto.getSortBy());
            this.natsClient.reply(msg, pageResponse.stream().toList());
        });

        this.natsClient.createDispatcher().subscribe("category.read_by_id", (msg)->{
            PathParamDto pathParamDto = this.natsClient.toJava(msg, PathParamDto.class);
            ResponseCategoryDto responseCategoryDto =  this.categoryService.findOne(pathParamDto.getId());
            if(responseCategoryDto == null) {
                this.natsClient.rpcException(msg, StatusCode.NOT_FOUND, "Category not Found");
                return;
            }
            this.natsClient.reply(msg, responseCategoryDto);
        });

        this.natsClient.createDispatcher().subscribe("category.update", (msg)->{
            UpdateCategoryDto updateCategoryDto = this.natsClient.toJava(msg, UpdateCategoryDto.class);
            ResponseCategoryDto responseCategoryDto =  this.categoryService.update(updateCategoryDto,updateCategoryDto.getId());
            if(responseCategoryDto == null) {
                this.natsClient.rpcException(msg, StatusCode.NOT_FOUND, "Category not Found");
                return;
            }
            this.natsClient.reply(msg, responseCategoryDto);
        });

        this.natsClient.createDispatcher().subscribe("category.delete", (msg)->{
            PathParamDto pathParamDto = this.natsClient.toJava(msg, PathParamDto.class);
            this.categoryService.remove(pathParamDto.getId());
            this.natsClient.reply(msg, "");
        });

    }

}

  