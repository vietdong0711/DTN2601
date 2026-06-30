package com.vti.dto.csv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCsv {
    private String username;
    private String fullName;
    private String email;
    private String departmentID;
    private String positionID;
    private String errorMessage;

    public AccountCsv(String username, String fullName, String email, String departmentID, String positionID) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.departmentID = departmentID;
        this.positionID = positionID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(",");
        sb.append(fullName).append(",");
        sb.append(email).append(",");
        sb.append(departmentID).append(",");
        sb.append(positionID).append(",");
        sb.append(errorMessage);
        return sb.toString();
    }
}
