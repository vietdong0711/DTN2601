package backend;

import entity.Position;
import enums.PositionName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLPosition {
    // lấy ds các chuc vu trong DB và in ra
    public static void showPosition() throws ClassNotFoundException{
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
            // b2: lấy dữ liệu từ bảng position
            String sql = "select * from position;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            List<Position> positions = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("position_id");// lấy giá trị từ column position_id
                String name = rs.getString("position_name");//lấy giá trị từ column position_name
                PositionName positionName = PositionName.valueOf(name);
                Position po = new Position(id, positionName);
                positions.add(po);
            }
            System.out.println("+-----+--------------------+");
            System.out.printf("|%5s|%20s|\n", "ID", "Tên chức vụ");
            System.out.println("+-----+--------------------+");
            for (Position po : positions) {
                System.out.printf("|%5s|%20s|\n", po.getId(), po.getName());
            }
            System.out.println("+-----+--------------------+");

        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
        }
    }
}
