package org.example.backend.service;

import org.example.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();
    boolean create(String name);
    boolean update(int id, String name);
    boolean delete(int id);
}
