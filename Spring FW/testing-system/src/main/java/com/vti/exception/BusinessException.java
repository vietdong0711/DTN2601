package com.vti.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private  String message;
    private  String code;
    private  int status;
}
