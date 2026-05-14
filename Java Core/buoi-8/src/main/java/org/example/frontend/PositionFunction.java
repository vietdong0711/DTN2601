package org.example.frontend;

import org.example.backend.controller.PositionController;
import org.example.entity.Position;
import org.example.enums.PositionName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PositionFunction {
    // khoi tao positionController
    private PositionController positionController = new PositionController();
    private Scanner sc = new Scanner(System.in);


    public void run() {
        List<Position> positions = new ArrayList<>();
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds position");
            System.out.println("2. Thêm mới position");
            System.out.println("3. Xóa position theo id");
            System.out.println("4. Update position theo ID");
            System.out.println("8. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    positions = positionController.findAll();
                    this.showPosition(positions);
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
                case "8":
                    return;
                default:
                    System.out.println("Nhập sai, nhập lại.");
            }
        }
    }

    public void showPosition(List<Position> positions) {
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

    public void insertPosition() {
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
        boolean check = positionController.create(name);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }

    public void deletePosition() {
        System.out.println("Nhập ID chức vụ muốn xóa: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean check = positionController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }

    public void updatePosition() {
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

        boolean check = positionController.update(id, name);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update thất bại");
        }
    }
}

//auto_increment   id tu tang
//  1   2   3   4
// xoa 4 di
// insert theem 1 row nua
//  1   2   3   5


// 1    2   3   4
// xoa 3
// them moi
