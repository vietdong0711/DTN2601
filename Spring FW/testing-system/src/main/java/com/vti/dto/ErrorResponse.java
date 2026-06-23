package com.vti.dto;

import lombok.*;

@Data
@Builder
//@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private int status;
}
