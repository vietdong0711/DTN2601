package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {


    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/dtn2601_testing_system";
        String username = "root";
        String password = "root";// mk mysql
        Connection connection = null;
        try {
            // b1: kết nối đến DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
//            if (connection != null) {
//                System.out.println("Kết nối DB thành công");
//            }
        }  catch (Exception ex) {
            System.out.println("Kết nối DB ko thành công");
        }
        return connection;
    }
}
