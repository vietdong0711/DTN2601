package com.vti.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSearchForm {
    private String username;
    private String fullName;
    private String email;
    private String departmentName;
    private String positionName;
}
