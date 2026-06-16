package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`group`")
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id // khoa chinh
    @Column(name = "group_id")// đang trỏ tới column department_id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private Integer id;

    @Column(name = "group_name", nullable = false, unique = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Account creator;

    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
}
