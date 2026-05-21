package org.example.backend.controller;

import org.example.backend.service.IDepartmentService;
import org.example.backend.service.impl.DepartmentServiceImpl;
import org.example.entity.Department;

import java.util.List;

public class DepartmentController {
    // khởi tạo service
    IDepartmentService departmentService = new DepartmentServiceImpl();

    public List<Department> findAll() {
        return departmentService.findAll();// lấy ds từ service
    }

    public boolean create(String name) {
        return departmentService.create(name);
    }

    public boolean update(int id, String name) {
        return departmentService.update(id, name);
    }

    public boolean delete(int id) {
        return departmentService.delete(id);
    }

    public boolean checkExistName(String name, Integer id) {
        return departmentService.checkExistName(name, id);
    }

    public boolean checkExistId(Integer id) {
        return departmentService.checkExistId(id);
    }

    public String importDepartmentFromCSV(String pathName) {// trả về String; import thành công, import thất bại
        return departmentService.importDepartmentFromCSV(pathName);
    }
}
