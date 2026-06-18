package com.vti.form;

import com.vti.entity.Department;
import com.vti.entity.Position;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateForm {

    @NotBlank(message = "Username ko được để trống")
    @Length(max = 100, message = "Username ko được dài quá 100 kí tự")
    private String username;
    private String password;
    private String fullName;
    @Email(regexp = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+$", message = "Phải nhập đúng định dạng email")
    @Length(max = 100, message = "Email ko được dài quá 100 kí tự")
    private String email;
    private Integer departmentId;
    private Integer positionId;
}
