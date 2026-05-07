package entity;

import enums.GioiTinh;

import java.time.LocalDate;

public class CanBo {
    private String fullName;
    private int age;
    private GioiTinh gioiTinh;
    private String address;

    public CanBo() {

    }

    public CanBo(String fullName, int age, GioiTinh gioiTinh, String address) {
        this.fullName = fullName;
        this.age = age;
        this.gioiTinh = gioiTinh;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CanBo{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gioiTinh=" + gioiTinh +
                ", address='" + address + '\'' +
                '}';
    }
    public void show() {
    }
}
