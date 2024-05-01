package com.suarezr.products_msvc.common.exceptions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessageShort extends ErrorMessage {
    String message;
    String error;
    int statusCode;
}

  