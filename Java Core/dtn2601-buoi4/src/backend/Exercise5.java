package backend;

import entity.CanBo;
import entity.CongNhan;
import entity.KySu;
import entity.NhanVien;
import enums.GioiTinh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise5 {
//    Một đơn vị sản xuất gồm có các cán bộ là công nhân, kỹ sư, nhân viên.
//    Mỗi cán bộ cần quản lý các dữ liệu: Họ tên, tuổi, giới tính(name, nữ, khác), địa chỉ.
//    Cấp công nhân sẽ có thêm các thuộc tính riêng: Bậc (1 đến 10).
//    Cấp kỹ sư có thuộc tính riêng: Nghành đào tạo.
//    Các nhân viên có thuộc tính riêng: công việc.
//    Hãy xây dựng các lớp CongNhan, KySu, NhanVien kế thừa từ lớp CanBo.
//    Xây dựng lớp QLCB(quản lý cán bộ) cài đặt các phương thức thực hiện các chức năng sau:
//    a) Thêm mới cán bộ.
//    b) Tìm kiếm theo họ tên.
//    c) Hiện thị thông tin về danh sách các cán bộ.
//    d) Nhập vào tên của cán bộ và delete cán bộ đó
//    e) Thoát khỏi chương trình.
    public static void question1() {
        Scanner scanner = new Scanner(System.in);
        List<CanBo> canBos = new ArrayList<>();
        while (true) {
            System.out.println("== Mời bạn chọn chức năng ==");
            System.out.println("1. Thêm mới cán bộ");
            System.out.println("2. Tìm kiếm theo họ tên");
            System.out.println("3. Hiện thị thông tin về danh sách các cán bộ");
            System.out.println("4. Nhập vào tên của cán bộ và delete cán bộ đó");
            System.out.println("5. Thoát khỏi chương trình");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    System.out.println("Mời nhập họ tên: ");
                    String fullName = scanner.nextLine();
                    System.out.println("Mời nhập tuổi: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Mời nhập giới tính: 1. Nam  2. Nữ   Khác. Khác");
                    String inputGioiTinh = scanner.nextLine();
                    GioiTinh gioiTinh;
                    switch (inputGioiTinh) {
                        case "1":
                            gioiTinh = GioiTinh.NAM;
                            break;
                        case "2":
                            gioiTinh = GioiTinh.NU;
                            break;
                        default:
                            gioiTinh= GioiTinh.KHAC;
                    }

                    System.out.println("Mời nhập địa chỉ: ");
                    String address = scanner.nextLine();

                    System.out.println("== Mời bạn chọn cán bộ ==");
                    System.out.println("1. Công nhân");
                    System.out.println("2. Kỹ Sư");
                    System.out.println("Khác. Nhân viên");
                    String inputCanBo = scanner.nextLine();
                    switch (inputGioiTinh) {
                        case "1":
                            System.out.println("Nhập bậc (1-10): ");
                            int bac = scanner.nextInt();
                            scanner.nextLine();
                            CanBo congNhan = new CongNhan(fullName, age, gioiTinh, address, bac);
                            canBos.add(congNhan);
                            System.out.println("Thêm công nhân thành công");
                            break;
                        case "2":
                            System.out.println("Nhập nganh: ");
                            String nganh = scanner.nextLine();
                            CanBo kySu = new KySu(fullName, age, gioiTinh, address, nganh);
                            canBos.add(kySu);
                            System.out.println("Thêm kỹ sư thành công");
                            break;
                        default:
                            System.out.println("Nhập cong viec: ");
                            String congViec = scanner.nextLine();
                            CanBo nhanVien = new NhanVien(fullName, age, gioiTinh, address, congViec);
                            canBos.add(nhanVien);
                            System.out.println("Thêm nhân viên thành công");
                    }
                    break;
                case "2":
                    System.out.println("Nhập tên cần tìm: ");
                    String name = scanner.nextLine();

                    findByName(canBos, name);
                    break;
                case "3":
                    showCanBo(canBos);
                    break;
                case "4":
                    System.out.println("Nhập tên cần xóa: ");
                    String deleteName = scanner.nextLine();
                    deleteByName(canBos, deleteName);
                    break;
                case "5":
                    System.out.println("Thoát");
                    return;
//                    System.exit(0);
                default:
            }
        }



    }

    private static void showCanBo(List<CanBo> canBos) {
        // in
        System.out.println("+---------------+--------------------+-----+----------+--------------------+-----+---------------+---------------+");
        System.out.printf("|%15s|%20s|%5s|%10s|%20s|%5s|%15s|%15s|\n", "Loại", "Họ tên", "Tuổi",
                "Giới tính", "Địa chỉ", "Bậc", "Ngành", "Công việc");
        System.out.println("+---------------+--------------------+-----+----------+--------------------+-----+---------------+---------------+");
        for (CanBo canBo : canBos) {
            canBo.show();
        }
        System.out.println("+---------------+--------------------+-----+----------+--------------------+-----+---------------+---------------+");
    }

    private static void findByName(List<CanBo> canBos, String name) {
        boolean checkExists = false;
        for (CanBo canBo: canBos) {
            if (canBo.getFullName().equals(name)) {
                System.out.println(canBo);
                checkExists = true;
            }
        }
        if (!checkExists) {//checkExists == false
            System.out.println("Ko có tên trong hệ thống");
        }
    }


    private static void deleteByName(List<CanBo> canBos, String deleteName) {
        // tìm ra ds các cán bộ sẽ bị xóa
        List<CanBo> deletes = new ArrayList<>();
        for (CanBo cb: canBos) {
            if (cb.getFullName().equals(deleteName)) {
                deletes.add(cb);
            }
        }
        boolean checkDelete = canBos.removeAll(deletes);
//        boolean checkDelete = canBos.removeIf(i -> i.getFullName().equals(deleteName));

        if (checkDelete) {// check == true
            System.out.println("Đã xóa thành công");
        } else {
            System.out.println("Ko tồn tại tên trong hệ thống");
        }
    }

}
