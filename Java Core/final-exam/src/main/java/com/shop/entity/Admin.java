package com.shop.entity;

import com.shop.enums.Role;

public class Admin extends User {
    private int expInYear;

    public Admin(String fullName, String email, String password, Role role, int expInYear) {
        super(fullName, email, password, role);
        this.expInYear = expInYear;
    }

    public Admin(Long id, String fullName, String email, String password, Role role, int expInYear) {
        super(id, fullName, email, password, role);
        this.expInYear = expInYear;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "expInYear=" + expInYear +
                '}';
    }
}
