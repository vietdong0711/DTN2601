package org.example.backend.repository;

import org.example.entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    boolean create(String name);
    boolean update(int id, String name);
    boolean delete(int id);
    boolean checkExistName(String name, Integer id);
    boolean checkExistId(Integer id);

    boolean createDepartments(List<Department> departments) throws SQLException;
}
