package com.vti.service.impl;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired // khoi tao doi tuong
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer i) {
        Optional<Department> optional = departmentRepository.findById(i);
//        if (optional.isPresent()) {// co gtri
//            Department department = optional.get();
//            return department;
//        } else {// ko co gtri
//            return null;
//        }
        return optional.orElse(null);
    }

    @Override
    public Department findByName(String name) {
        Department department = departmentRepository.findByName(name);
        return department;
    }

    @Override
    public Department create(Department department) {
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Department update(Integer id, Department department) {
        // tìm department theo id đưa vào
        Department departmentUpdate = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(departmentUpdate)) {
            throw new RuntimeException("ID ko tồn tại");
        }
        //  update tên mới vào
        if (departmentRepository.existsByNameAndIdNot(department.getName(), id)) {
            throw new RuntimeException("Tên này đã tồn tại");
        }
        departmentUpdate.setName(department.getName());
        departmentRepository.save(departmentUpdate);
        return departmentUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        // validation
        departmentRepository.deleteById(id);
    }


}
