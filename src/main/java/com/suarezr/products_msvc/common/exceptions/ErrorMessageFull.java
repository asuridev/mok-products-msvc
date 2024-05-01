package com.suarezr.products_msvc.common.exceptions;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessageFull extends ErrorMessage {
    private List<String> message;
    private  String error;
    private int statusCode;
}
  