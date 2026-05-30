package org.example.dto.csv;

import org.example.entity.Department;
import org.example.entity.Position;

public class AccountCsv {
    private String username;
    private String fullName;
    private String email;
    private String departmentID;
    private String positionID;

    public AccountCsv(String username, String fullName, String email, String departmentID, String positionID) {
        this.departmentID = departmentID;
        this.email = email;
        this.fullName = fullName;
        this.positionID = positionID;
        this.username = username;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
