package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import enums.PositionName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QLAccount {
    // lấy ds các account trong DB và in ra
    public static void showAccount() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/dtn2601_testing_system";
        String username = "root";
        String password = "root";// mk mysql

        try {
            // b1: kết nối đến DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Kết nối DB thành công");
            }
            // b2: lấy dữ liệu từ bảng account
            String sql = "select acc.*, de.department_name, po.position_name \n" +
                    "from account acc \n" +
                    "left join department de on acc.department_id = de.department_id \n" +
                    "left join position po on acc.position_id = po.position_id;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            List<Account> accounts = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
            while (rs.next()) {// lặp qua qua từng dòng của rs
                Integer id = rs.getInt("account_id");// lấy giá trị từ cloumn account_id
                String email = rs.getString("email");//lấy giá trị từ cloumn account_name
                String userName = rs.getString("username");
                String fullName = rs.getString("full_name");
                Integer departmentID = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                Integer positionID = rs.getInt("position_id");
                String positionName = rs.getString("position_name");
                Date createDate = rs.getDate("create_date");

                Department department = new Department(departmentID, departmentName);
                Position position = new Position(positionID, PositionName.valueOf(positionName));

                Account account = new Account(id, username, fullName, email, department, position, createDate);
                accounts.add(account);
            }

            System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
            System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", "ID", "FullName", "Email", "Username", "Tên phòng ban", "Tên chức vụ");
            System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
            for (Account account : accounts) {
                System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", account.getId(), account.getFullName(), account.getEmail(), account.getUsername(), account.getDepartment().getName(), account.getPosition().getName().name());
            }
            System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");


        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
    }
}
