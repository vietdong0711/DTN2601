package org.example.backend.service;

import org.example.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    boolean create(String email, String username, String fullName, int departmentID, int positionID);

    boolean update(int id, String updateName, String email, String username, int departmentId, int positionId);

    boolean delete(int id);
}
