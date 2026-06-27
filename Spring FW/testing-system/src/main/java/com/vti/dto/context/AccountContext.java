package com.vti.dto.context;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class AccountContext {
    private Map<String, Account> mapAccountByEmail;
    private Map<String, Account> mapAccountByUsername;
    private List<Department> departments;
    private List<Position> positions;
}
