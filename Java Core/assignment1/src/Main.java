import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Account account1 = new Account();// khởi tạo đối tượng acc
        // set giá trị cho từng thuộc tính
        account1.accountID = 1;
        account1.username = "dongnv";
        account1.fullName = "Nguyễn Viết Đồng";
        account1.createDate = LocalDate.now();
        account1.address = "Hà Tĩnh";
        account1.gender = Account.GioiTinh.MALE;
        account1.diemToan = 8.0f;
        float[] arrs = {7.1f, 8.0f, 7.7f};
        account1.diems = arrs;
        account1.isPassCourse = true;

        // in ra các gtri của account1
        System.out.println("ID: " + account1.accountID);
        System.out.println("Username: " + account1.username);
        System.out.println("Fullname: " + account1.fullName);
        System.out.println("Create Date: " + account1.createDate);
        System.out.println("Address: " + account1.address);
        System.out.println("Gender: " + account1.gender.name());
        System.out.println("Điểm toán: " + account1.diemToan);
        System.out.println("Điểm: " + account1.diems);
        for (int i = 0; i < account1.diems.length; i++) {
            System.out.print(account1.diems[i] + " ");
        }
        System.out.println("IsPassCourse: " + account1.isPassCourse);

    }
}