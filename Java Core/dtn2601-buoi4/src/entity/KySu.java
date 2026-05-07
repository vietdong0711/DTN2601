package entity;

import enums.GioiTinh;

public class KySu extends CanBo {
    private String nganh;

    public KySu() {

    }

    public KySu(String fullName, int age, GioiTinh gioiTinh, String address, String nganh) {
        super(fullName, age, gioiTinh, address);
        this.nganh = nganh;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    @Override
    public String toString() {
        return "==Kỹ sư==   Họ tên: " + super.getFullName() + ", Tuổi: " + super.getAge() +
                ", Giới tính: " + super.getGioiTinh() + ", Địa chỉ: " + super.getAddress() +
                ", Bậc: " + this.nganh;
    }

    public void show() {
        System.out.printf("|%15s|%20s|%5s|%10s|%20s|%5s|%15s|%15s|\n", "Kỹ Sư", super.getFullName(), super.getAge(),
                super.getGioiTinh(), super.getAddress(), " ", this.nganh, "");
    }
}
