package backend;


import entity.Department;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment {

    // lấy ds các phòng ban trong DB và in ra
    public static void showDepartment() throws ClassNotFoundException{
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
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from department;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            List<Department> departments = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ cloumn department_id
                String name = rs.getString("department_name");//lấy giá trị từ cloumn department_name
                Department dep = new Department(id, name);
                departments.add(dep);
            }
            System.out.println("+-----+--------------------+");
            System.out.printf("|%5s|%20s|\n", "ID", "Tên phòng ban");
            System.out.println("+-----+--------------------+");
            for (Department department : departments) {
                System.out.printf("|%5s|%20s|\n", department.getId(), department.getName());
            }
            System.out.println("+-----+--------------------+");

        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
        }
    }

}
