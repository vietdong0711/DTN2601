import java.time.LocalDate;

public class Account {
    //thuộc tính - đặc điểm để nhận diện các đối tượng với nhau
    int accountID;//1,23,4,,56,
    String username;
    String fullName;
    LocalDate createDate;
    String address;
    GioiTinh gender;
    // mảng điểm
    float diemToan;// chỉ là 1 gtri
    float[] diems;// 1 mảng giá trị
    boolean isPassCourse;


    public enum GioiTinh {
        MALE, FEMALE
    }

    public enum PositionName {
        DEV, TEST, PM, SCRUM_MASTER
    }




    // phương thức - hành động của đối tượng
    public void an() {
        System.out.println("đang ăn");
    }

    public void ngu() {
        System.out.println("đang ngủ");
    }
}
