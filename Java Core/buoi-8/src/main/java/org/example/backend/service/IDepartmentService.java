package org.example.backend.service;

import org.example.dto.context.DepartmentContext;
import org.example.dto.csv.DepartmentCsv;
import org.example.entity.Department;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface IDepartmentService extends ImportFile<DepartmentCsv, DepartmentContext, Department> {
    List<Department> findAll();
    boolean create(String name);
    boolean update(int id, String name);
    boolean delete(int id);
    boolean checkExistName(String name, Integer id);
    boolean checkExistId(Integer id);

    String importDepartmentFromCSV(String pathName);
}
