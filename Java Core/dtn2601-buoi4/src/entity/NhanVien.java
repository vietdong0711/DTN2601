package entity;

import enums.GioiTinh;

public class NhanVien extends CanBo {
    private String congViec;

    public NhanVien() {

    }

    public NhanVien(String fullName, int age, GioiTinh gioiTinh, String address, String congViec) {
        super(fullName, age, gioiTinh, address);
        this.congViec = congViec;
    }

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }

    @Override
    public String toString() {
        return "==Nhân viên==   Họ tên: " + super.getFullName() + ", Tuổi: " + super.getAge() +
                ", Giới tính: " + super.getGioiTinh() + ", Địa chỉ: "+ super.getAddress() +
                ", Bậc: " + this.congViec;
    }

    public void show() {
        System.out.printf("|%15s|%20s|%5s|%10s|%20s|%5s|%15s|%15s|\n", "Nhân viên", super.getFullName(), super.getAge(),
                super.getGioiTinh(), super.getAddress(), " ", "", this.congViec);
    }
}
