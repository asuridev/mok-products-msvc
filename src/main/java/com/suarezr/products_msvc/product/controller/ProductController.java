package com.suarezr.products_msvc.product.controller;

import com.suarezr.products_msvc.common.dtos.PaginationDto;
import com.suarezr.products_msvc.common.dtos.PathParamDto;
import com.suarezr.products_msvc.common.nats.NatsClient;
import com.suarezr.products_msvc.common.nats.StatusCode;
import com.suarezr.products_msvc.product.services.ProductService;
import com.suarezr.products_msvc.product.services.dtos.CreateProductDto;
import com.suarezr.products_msvc.product.services.dtos.UpdateProductDto;
import com.suarezr.products_msvc.product.services.dtos.ResponseProductDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;



@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;
    private final NatsClient natsClient;

    @PostConstruct
    void start(){

        this.natsClient.createDispatcher().subscribe("product.create", (msg)->{
            CreateProductDto createProductDto = this.natsClient.toJava(msg,CreateProductDto.class);
            ResponseProductDto newProduct = this.productService.create(createProductDto);
            if(newProduct == null) {
                this.natsClient.rpcException(msg, StatusCode.NOT_FOUND, "The category entered does not exist");
                return;
            }
            ResponseProductDto responseProductDto =  this.productService.findOne(newProduct.getId());
            this.natsClient.reply(msg, responseProductDto);
        });

        this.natsClient.createDispatcher().subscribe("product.read", (msg)->{
            PaginationDto paginationDto = this.natsClient.toJava(msg, PaginationDto.class);
            Page<ResponseProductDto> pageResponse = this.productService.findAll(paginationDto.getPage(), paginationDto.getLimit(),paginationDto.getSortBy());
            this.natsClient.reply(msg, pageResponse.stream().toList());
        });

        this.natsClient.createDispatcher().subscribe("product.read_by_id", (msg)->{
            PathParamDto pathParamDto = this.natsClient.toJava(msg, PathParamDto.class);
            ResponseProductDto responseProductDto =  this.productService.findOne(pathParamDto.getId());
            if(responseProductDto == null) {
                this.natsClient.rpcException(msg, StatusCode.NOT_FOUND, "Product Not Found");
                return;
            }
            this.natsClient.reply(msg, responseProductDto);
        });

        this.natsClient.createDispatcher().subscribe("product.update", (msg)->{
            UpdateProductDto updateProductDto = this.natsClient.toJava(msg, UpdateProductDto.class);
            ResponseProductDto responseProductDto =  this.productService.update(updateProductDto,updateProductDto.getId());
            if(responseProductDto == null) {
                this.natsClient.rpcException(msg, StatusCode.NOT_FOUND, "Product not Found");
                return;
            }
            this.natsClient.reply(msg, responseProductDto);
        });

        this.natsClient.createDispatcher().subscribe("product.delete", (msg)->{
            PathParamDto pathParamDto = this.natsClient.toJava(msg, PathParamDto.class);
            this.productService.remove(pathParamDto.getId());
            this.natsClient.reply(msg, "");
        });
    }


}

  