package org.example.frontend;

import org.example.backend.controller.DepartmentController;
import org.example.entity.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentFunction {
    // khoi tao controller
    DepartmentController departmentController = new DepartmentController();
    private Scanner sc = new Scanner(System.in);

    public void run() {
        List<Department> departments = new ArrayList<>();
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds department");
            System.out.println("2. Thêm mới department");
            System.out.println("3. Xóa department theo id");
            System.out.println("4. Update department theo ID");
            System.out.println("8. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    departments = departmentController.findAll();
                    this.showDepartment(departments);
                    break;
                case "2":
                    insertDepartment();
                    break;
                case "3":
                    deleteDepartment();
                    break;
                case "4":
                    updateDepartment();
                    break;
//                case "5":
//                    findByIdAndName();
//                    break;
//                case "6":
//                    findDepartmentTheMostEmployee();
//                    break;
//                case "7":
//                    departments = QLDepartment.findDepartmentTheLeastEmployee();
//                    showDepartment(departments);
//                    break;
                case "8":
                    return;
                default:
                    System.out.println("Nhập sai, nhập lại.");
            }
        }

    }

    public void showDepartment(List<Department> departments) {
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Tên phòng ban");
        System.out.println("+-----+--------------------+");
        for (Department department : departments) {
            System.out.printf("|%5s|%20s|\n", department.getId(), department.getName());
        }
        if (departments.size() == 0) {
            System.out.println("Không tìm thấy");
        }
        System.out.println("+-----+--------------------+");
    }

    public void insertDepartment() {
        System.out.println("Nhập tên phòng ban: ");
        String name = sc.nextLine();
        boolean check = departmentController.create(name);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }

    public void deleteDepartment() {
        System.out.println("Nhập ID phòng ban cần xóa: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean check = departmentController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }

    public void updateDepartment() {
        System.out.println("Nhập tên ID phòng ban cần sửa: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập tên phòng ban muốn thay đổi: ");
        String name = sc.nextLine();

        boolean check = departmentController.update(id, name);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update thất bại");
        }
    }
}
