package com.vti.dto;

import com.vti.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String username;
    private String fullName;
    private String email;
    private String departmentName;
    private String positionName;
    private Date createDate;

    public AccountDTO(Account account) {
        this.createDate = account.getCreateDate();
        if (Objects.nonNull(account.getDepartment())) {
            this.departmentName = account.getDepartment().getName();
        }
        this.email = account.getEmail();
        this.fullName = account.getFullName();
        if (Objects.nonNull(account.getPosition())) {
            this.positionName = account.getPosition().getName().name();
        }
        this.username = account.getUsername();
    }
}
