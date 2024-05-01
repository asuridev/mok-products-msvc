package com.suarezr.products_msvc.common.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(){

    }
    public UnauthorizedException(String message) {
        super(message);
    }
}
  