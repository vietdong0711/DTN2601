package com.vti.dto;

import com.vti.dto.csv.AccountCsv;
import lombok.Data;

import java.util.List;

@Data
public class AccountImportResponse {
    private String message;
    private List<AccountCsv> errors;

    public AccountImportResponse(String message, List<AccountCsv> errors) {
        this.message = message;
        this.errors = errors;
    }
}
