package org.example.backend.controller;

import org.example.backend.service.IAccountService;
import org.example.backend.service.impl.AccountServiceImpl;
import org.example.entity.Account;

import java.util.List;

public class AccountController {
    // khoi tao accountService
    private IAccountService accountService = new AccountServiceImpl();


    public List<Account> findAll() {
        return accountService.findAll();
    }

    public boolean create(String email, String username, String fullName, int departmentID, int positionID) {
        return accountService.create(email, username, fullName, departmentID, positionID);
    }

    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId) {
        return accountService.update(id, updateName, email, username, departmentId, positionId);
    }

    public boolean delete(int id) {
        return accountService.delete(id);
    }
}
