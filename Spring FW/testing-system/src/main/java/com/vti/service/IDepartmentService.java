package com.vti.service;

import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    Department findById(Integer i);

    Department findByName(String name);

    Department create(Department department);// return ra department vừa tạo

    Department update(Integer id,Department department);

    void deleteById(Integer id);
}
