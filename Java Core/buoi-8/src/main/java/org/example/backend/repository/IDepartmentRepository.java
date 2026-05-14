package org.example.backend.repository;

import org.example.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    boolean create(String name);
    boolean update(int id, String name);
    boolean delete(int id);
    boolean checkExistName(String name, Integer id);
}
