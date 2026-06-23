package com.vti.exception;

import com.vti.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
//        ErrorResponse response = new ErrorResponse();
//        response.setCode(e.getCode());
//        response.setMessage(e.getMessage());
//        response.setStatus(e.getStatus());

        ErrorResponse response = ErrorResponse.builder()
                .message(e.getMessage())
                .code(e.getCode())
                .status(e.getStatus())
                .build();
        return new ResponseEntity<>(response
                , HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .message("Internal Server Error")
                .code("INTERNAL_SERVER_ERROR")
                .status(500)
                .build();
        return new ResponseEntity<>(response
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
