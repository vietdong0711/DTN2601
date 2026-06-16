package com.vti.entity;

import com.vti.enums.ArticlePositionNameConverter;
import com.vti.enums.PositionName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "position")
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id // khoa chinh
    @Column(name = "position_id")// đang trỏ tới column position_id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private Integer id;

    @Enumerated(EnumType.STRING)//STRING: java show ntn thì DB lưu như vậy, ORDINAL: lưu thành số theo vị của enum
//    @Convert(converter = ArticlePositionNameConverter.class)
    @Column(name = "position_name", nullable = false) // not null unique, 100 kí tự
    private PositionName name;
}
