package frontend;

import backend.QLDepartment;
import backend.QLPosition;
import entity.Department;
import entity.Position;
import enums.PositionName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PositionFunction {
    private static Scanner sc = new Scanner(System.in);


    public static void run() {
        List<Position> positions = new ArrayList<>();
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds position");
            System.out.println("2. Thêm mới position");
            System.out.println("3. Xóa position theo tên");
            System.out.println("4. Update position theo ID");
            System.out.println("5. Tìm kiếm position theo ID và tên chức vụ");
            System.out.println("6. Ds chức vụ có nhiều nhân viên nhất");
            System.out.println("8. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    positions = QLPosition.findAllPosition();
                    showPosition(positions);
                    break;
                case "2":
                    insertPosition();
                    break;
                case "3":
                    deletePosition();
                    break;
                case "4":
                    updatePosition();
                    break;
                case "5":
                    findByName();
                    break;
                case "6":
                    findByName();
                    break;
                case "7":
                    findPositionTheMostEmployee();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Nhập sai, nhập lại.");
            }
        }
    }

    public static void findPositionTheMostEmployee() {
        List<Position> positions = QLPosition.findPositionTheMostEmployee();
        showPosition(positions);
    }



    public static void findByName() {
        System.out.println("Nhập tên chức vụ cần tìm: ");
        String name = sc.nextLine();

        List<Position> positions = QLPosition.findByName(name);
        showPosition(positions);
    }


    public static void showPosition(List<Position> positions) {
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Tên chức vụ");
        System.out.println("+-----+--------------------+");
        for (Position position : positions) {
            System.out.printf("|%5s|%20s|\n", position.getId(), position.getName());
        }
        if (positions.isEmpty()) {
            System.out.println("Không tìm thấy");
        }
        System.out.println("+-----+--------------------+");
    }

    public static void insertPosition() {
        System.out.println("Nhập tên chức vụ: 1.DEV     2.TEST      3.SCRUM_MASTER      4.PM");
        String choice = sc.nextLine();
        PositionName name;
        switch (choice) {
            case "1":
                name = PositionName.DEV;
                break;
            case "2":
                name = PositionName.TEST;
                break;
            case "3":
                name = PositionName.SCRUM_MASTER;
                break;
            default:
                name = PositionName.PM;

        }
        boolean check = QLPosition.insertPosition(name);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }

    public static void deletePosition() {
        System.out.println("Nhập tên chức vụ: 1.DEV     2.TEST      3.SCRUM_MASTER      4.PM");
        String choice = sc.nextLine();
        PositionName name;
        switch (choice) {
            case "1":
                name = PositionName.DEV;
                break;
            case "2":
                name = PositionName.TEST;
                break;
            case "3":
                name = PositionName.SCRUM_MASTER;
                break;
            default:
                name = PositionName.PM;
        }
        boolean check = QLPosition.deletePosition(name);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }


    public static void updatePosition() {
        System.out.println("Nhập tên ID chức vụ cần sửa: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập tên chức vụ: 1.DEV     2.TEST      3.SCRUM_MASTER      4.PM");
        String choice = sc.nextLine();
        PositionName name;
        switch (choice) {
            case "1":
                name = PositionName.DEV;
                break;
            case "2":
                name = PositionName.TEST;
                break;
            case "3":
                name = PositionName.SCRUM_MASTER;
                break;
            default:
                name = PositionName.PM;

        }

        boolean check = QLPosition.updatePosition(id, name);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update thất bại");
        }
    }
}
