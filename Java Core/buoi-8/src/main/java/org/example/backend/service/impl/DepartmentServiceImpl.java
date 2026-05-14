package org.example.backend.service.impl;

import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.entity.Department;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    // khởi tạo repository
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();

    @Override
    public List<Department> findAll() {
        // che dấu đi thông tin (ẩn đi thông tin)
        return departmentRepository.findAll();// lấy dc ds từ repository
    }

    @Override
    public boolean create(String name) {
        return departmentRepository.create(name);
    }

    @Override
    public boolean update(int id, String name) {
        return departmentRepository.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return departmentRepository.delete(id);
    }

    @Override
    public boolean checkExistName(String name, Integer id) {
        return departmentRepository.checkExistName(name, id);
    }
}
