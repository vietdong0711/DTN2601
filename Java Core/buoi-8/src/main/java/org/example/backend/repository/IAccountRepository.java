package org.example.backend.repository;

import org.example.entity.Account;

import java.util.List;
import java.util.Map;

public interface IAccountRepository {
    List<Account> findAll();

    boolean create(String email, String username, String fullName, int departmentID, int positionID);

    boolean update(int id, String updateName, String email, String username, int departmentId, int positionId);

    boolean delete(int id);

    Map<String, Account> mapAccountByUsername();
}
