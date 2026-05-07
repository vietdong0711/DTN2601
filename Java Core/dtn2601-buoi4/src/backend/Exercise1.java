package backend;

import entity.CanBo;
import enums.GioiTinh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise1 {

    // kết nối đén DB và laasy ra ds các cán bộ trong DB
    public static void getCanBos() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/qlcb";
        String username = "root";
        String password = "dong";// mk mysql

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("Kết nối thanh cong");
        }

        String sql = "select * from can_bo;";
        Statement statement= connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        List<CanBo> canBos = new ArrayList<>();// ds này đẻ lưu các cán bộ từ DB

        while (resultSet.next()) {
            String fullName = resultSet.getString(1);
            int age = resultSet.getInt("age");

            String gtinh = resultSet.getString(3);
            // chuyển tu String thành enum GioiTinh
            GioiTinh gioiTinh = GioiTinh.valueOf(gtinh);
            String address = resultSet.getString("address");

            CanBo canBo = new CanBo(fullName, age, gioiTinh, address);
            canBos.add(canBo);
        }

        System.out.println("Danh sách cán bộ là: ");
        for (CanBo canBo: canBos) {
            System.out.println(canBo.toString());
        }
    }
}
