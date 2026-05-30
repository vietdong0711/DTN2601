package com.shop.backend.service.impl;

import com.shop.backend.repository.IUserRepository;
import com.shop.backend.repository.impl.UserRepositoryImpl;
import com.shop.backend.service.IUserService;
import com.shop.entity.User;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserRepository repository = new UserRepositoryImpl();

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public User login(String email, String password) {
        return repository.login(email, password);
    }

    @Override
    public boolean create(String fullName, String email) {
        return repository.create(fullName, email);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return repository.checkExistEmail(email);
    }
}
