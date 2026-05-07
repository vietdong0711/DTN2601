package entity;

import enums.GioiTinh;

public class CongNhan extends CanBo {
    private int bac;

    public CongNhan() {

    }

    public CongNhan(String fullName, int age, GioiTinh gioiTinh, String address, int bac) {
        super(fullName, age, gioiTinh, address);
        this.bac = bac;
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }

    @Override
    public String toString() {
        return "==Công nhân==   Họ tên: " + super.getFullName() + ", Tuổi: " + super.getAge() +
                ", Giới tính: " + super.getGioiTinh() + ", Địa chỉ: "+ super.getAddress() +
                ", Bậc: " + this.bac;
    }

    public void show() {
        System.out.printf("|%15s|%20s|%5s|%10s|%20s|%5s|%15s|%15s|\n", "Công Nhân", super.getFullName(), super.getAge(),
                super.getGioiTinh(), super.getAddress(), this.bac, "", "");
    }
}
