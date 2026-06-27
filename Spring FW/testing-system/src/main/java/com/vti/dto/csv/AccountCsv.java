package com.vti.dto.csv;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCsv {
    private String username;
    private String fullName;
    private String email;
    private String departmentID;
    private String positionID;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(",");
        sb.append(fullName).append(",");
        sb.append(email).append(",");
        sb.append(departmentID).append(",");
        sb.append(positionID);
        return sb.toString();
    }
}
