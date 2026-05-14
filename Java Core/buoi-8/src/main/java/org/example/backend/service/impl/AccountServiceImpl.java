package org.example.backend.service.impl;

import org.example.backend.repository.impl.AccountRepositoryImpl;
import org.example.backend.service.IAccountService;
import org.example.entity.Account;

import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements IAccountService {
    // khoi tao accountRepository
    private AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(String email, String username, String fullName, int departmentID, int positionID) {
        return accountRepository.create(email, username, fullName, departmentID, positionID);
    }

    @Override
    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId) {
        return accountRepository.update(id, updateName, email, username, departmentId, positionId);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public Map<String, Account> mapAccountByUsername() {
        return accountRepository.mapAccountByUsername();
    }
}
