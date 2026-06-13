package com.vti.controller;

import com.vti.entity.Department;
import com.vti.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // xem thông tin department theo id   http://localhost:8080/api/departments/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<Department> findById(@PathVariable(name = "id") Integer i) {
        Department department = departmentService.findById(i);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // tìm kiem theo tên phong ban    name like name
    @GetMapping("/search")
    public ResponseEntity<Department> findByName(@RequestParam String name) {
        Department department = departmentService.findByName(name);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // tạo mới 1 department
    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        departmentService.create(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    // update tên phòng ban của id 19 thành hello
    @PutMapping(value = "/{id}")
    public ResponseEntity<Department> update(@PathVariable(name = "id") Integer id
            , @RequestBody Department department) {
        departmentService.update(id, department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // xóa phong ban
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
