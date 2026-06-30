package com.vti.form;

import com.vti.dto.csv.AccountCsv;
import lombok.Data;

import java.util.List;

@Data
public class AccountExportErros {
    private List<AccountCsv> errors;
}
