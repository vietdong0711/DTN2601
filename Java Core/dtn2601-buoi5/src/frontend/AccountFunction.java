package frontend;

import backend.QLAccount;
import backend.QLDepartment;
import backend.QLPosition;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountFunction {
    private static Scanner sc = new Scanner(System.in);

    public static void run() {
        List<Account> accounts = new ArrayList<>();
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds account");
            System.out.println("2. Thêm mới account");
            System.out.println("3. Xóa account theo tên");
            System.out.println("4. Update account theo ID");
            System.out.println("5. Tìm kiếm account họ tên");
            System.out.println("6. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    accounts = QLAccount.findAllAccount();
                    showAccount(accounts);
                    break;
                case "2":
                    insertAccount();
                    break;
                case "3":
                    deleteAccount();
                    break;
                case "4":
                    updateAccount();
                    break;
                case "5":
                    findByIdAndName();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Nhập sai, nhập lại.");
            }
        }
    }

    public static void findByIdAndName() {
        System.out.println("Nhập tên cần tìm: ");
        String name = sc.nextLine();

        List<Account> accounts = QLAccount.findByName(name);
        showAccount(accounts);
    }


    public static void showAccount(List<Account> accounts) {
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", "ID", "FullName", "Email", "Username", "Tên", "Tên chức vụ");
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (Account account : accounts) {
            System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", account.getId(), account.getFullName(), account.getEmail(), account.getUsername(), account.getDepartment().getName(), account.getPosition().getName().name());
        }
        if (accounts.isEmpty()) {
            System.out.println("Không tìm thấy");
        }
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
    }

    public static void insertAccount() {
        System.out.println("Nhập email: ");
        String email = sc.nextLine();
        System.out.println("Nhập username: ");
        String username = sc.nextLine();
        System.out.println("Nhập fullName: ");
        String fullName = sc.nextLine();
        System.out.println("Chọn ID department: ");
        List<Department> departments = QLDepartment.showDepartment();
        String depID;
        while (true) {
            for (Department department : departments) {
                System.out.println("ID: " + department.getId() + ", DepartmentName: " + department.getName());
            }
            depID = sc.nextLine();
            boolean checkExists = checkExistDepartment(departments, depID);
            if (!checkExists) {
                System.out.println("Chọn sai, chọn lại!");
            } else {
                break;
            }
        }

        System.out.println("Chọn ID position: ");
        List<Position> positions = QLPosition.findAllPosition();
        String poID;
        while (true) {
            for (Position position : positions) {
                System.out.println("ID: " + position.getId() + ", PositionName: " + position.getName());
            }
            poID = sc.nextLine();
            boolean checkExists = checkExistPosition(positions, poID);
            if (!checkExists) {
                System.out.println("Chọn sai, chọn lại!");
            } else {
                break;
            }
        }
        boolean check = QLAccount.insertAccount(email, username, fullName, Integer.parseInt(depID), Integer.parseInt(poID));
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }


    public static void deleteAccount() {
        System.out.println("Nhập tên cần xóa: ");
        String name = sc.nextLine();
        boolean check = QLAccount.deleteAccount(name);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }


    public static void updateAccount() {
        System.out.println("Nhập ID cần sửa: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập email: ");
        String email = sc.nextLine();
        System.out.println("Nhập username: ");
        String username = sc.nextLine();
        System.out.println("Nhập fullName: ");
        String fullName = sc.nextLine();
        System.out.println("Chọn ID department: ");
        List<Department> departments = QLDepartment.showDepartment();
        String depID;
        while (true) {
            for (Department department : departments) {
                System.out.println("ID: " + department.getId() + ", DepartmentName: " + department.getName());
            }
            depID = sc.nextLine();
            boolean checkExists = checkExistDepartment(departments, depID);
            if (!checkExists) {
                System.out.println("Chọn sai, chọn lại!");
            } else {
                break;
            }
        }

        System.out.println("Chọn ID position: ");
        List<Position> positions = QLPosition.findAllPosition();
        String poID;
        while (true) {
            for (Position position : positions) {
                System.out.println("ID: " + position.getId() + ", PositionName: " + position.getName());
            }
            poID = sc.nextLine();
            boolean checkExists = checkExistPosition(positions, poID);
            if (!checkExists) {
                System.out.println("Chọn sai, chọn lại!");
            } else {
                break;
            }
        }

        boolean check = QLAccount.updateAccount(id, fullName, email, username, Integer.parseInt(depID), Integer.parseInt(poID));
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update thất bại");
        }
    }

    public static boolean checkExistDepartment(List<Department> departments, String id) {
        for (Department department : departments) {
            if (id.equals(String.valueOf(department.getId()))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkExistPosition(List<Position> positions, String id) {
        for (Position position : positions) {
            if (id.equals(String.valueOf(position.getId()))) {
                return true;
            }
        }
        return false;
    }
}
