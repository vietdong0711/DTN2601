package entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Account {
    private int accountID;
    private String userName;
    private String email;
    private String fullname;
    private Department department;
    private Position position;
    private LocalDate createDate;
//    private Date createDate;

    public Account() {

    }

    public Account(int accountID, LocalDate createDate, Department department, String email, String fullname, Position position, String userName) {
        this.accountID = accountID;
        this.createDate = createDate;
        this.department = department;
        this.email = email;
        this.fullname = fullname;
        this.position = position;
        this.userName = userName;
    }

    public Account(int accountID, String email, String userName, String firstName, String lastName) {
        this.accountID = accountID;
        this.fullname = new StringBuilder(lastName).append(lastName).toString();
        this.email = email;
        this.userName = userName;
    }

    public Account(int accountID, String email, String userName, String firstName, String lastName, Position position) {
        this.accountID = accountID;
        this.fullname = new StringBuilder(lastName).append(lastName).toString();
        this.email = email;
        this.userName = userName;
        this.position = position;
        this.createDate = LocalDate.now();
//        this.createDate = new Date();
    }

    public Account(int accountID, String email, String userName, String firstName, String lastName, Position position, LocalDate createDate) {
        this.accountID = accountID;
        this.fullname = new StringBuilder(lastName).append(lastName).toString();
        this.email = email;
        this.userName = userName;
        this.position = position;
        this.createDate = createDate;
//        this.createDate = new Date();
    }

    public Account(String userName) {
        this.userName = userName;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "demo.Account{" +
                "accountID=" + accountID +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", fullname='" + fullname + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountID == account.accountID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, userName, department, position, createDate);
    }


    public void diHoc() {
    }
}
