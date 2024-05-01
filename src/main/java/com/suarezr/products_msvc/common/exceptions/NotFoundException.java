package com.suarezr.products_msvc.common.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){

    }
    public NotFoundException(String message) {
        super(message);
    }
}
  