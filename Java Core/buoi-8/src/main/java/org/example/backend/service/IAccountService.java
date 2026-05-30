package org.example.backend.service;

import org.example.dto.context.AccountContext;
import org.example.dto.csv.AccountCsv;
import org.example.entity.Account;

import java.util.List;
import java.util.Map;

public interface IAccountService extends ImportFile<AccountCsv, AccountContext, Account> {
    List<Account> findAll();

    boolean create(String email, String username, String fullName, int departmentID, int positionID);

    boolean update(int id, String updateName, String email, String username, int departmentId, int positionId);

    boolean delete(int id);

    Map<String, Account> mapAccountByUsername();

    boolean checkUsernameAndIdNot(String username, Integer id);

    boolean checkEmail(String email);

    boolean checkId(Integer id);

    boolean update(Integer id, String username);

    String importAccountFromCSV(String pathName);
}
