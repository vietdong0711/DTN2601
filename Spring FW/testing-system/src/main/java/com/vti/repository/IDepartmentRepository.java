package com.vti.repository;

import com.vti.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByName(String name);
    Department findByNameAndId(String name, Integer id);
    // kierm tra email tòn tai chưa
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    List<Department> findAllByNameLike(String name);
}
