package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id // khoa chinh
    @Column(name = "department_id")// đang trỏ tới column department_id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private Integer id;

    @Column(name = "department_name", nullable = false, unique = true, length = 100) // not null unique, 100 kí tự
    private String name;

//    @OneToMany(mappedBy = "department")
//    private List<Account> accounts;
}
