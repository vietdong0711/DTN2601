package backend;


import entity.Department;
import entity.Position;
import enums.PositionName;
import utils.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment {

    // lấy ds các phòng ban trong DB và in ra
    public static List<Department> showDepartment() {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
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
            return departments;

        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return new ArrayList<>();
    }

    // lấy ra ds các phòng ban theo departmentID và departmentName
    // đưa vào departmentID = 1 và departmentName= Marketing, tìm các phòng ban có thông tin như trên
    // "select * from department where department_id = 1 and department_name like 'Marketing';"
    // "select * from department where department_id = 1 or department_name like 'Marketing';"
    public static List<Department> findByDepartmentIdAndName(int searchId, String searchName) {
        List<Department> departments = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from department where department_id = ? and department_name like ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            // set gia trị cho từng dấu ?
            prepareStatement.setInt(1, searchId);
            prepareStatement.setString(2, searchName);

            ResultSet rs = prepareStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs

            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ column department_id
                String name = rs.getString("department_name");//lấy giá trị từ column department_name

                Department dep = new Department(id, name);
                departments.add(dep);
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return departments;
    }

    // với các bài toán thêm , sửa, xóa  thfi trả về boolean,
    // với bài toán tìm kiếm, trả 1 ds
    // thêm 1 department mới, người dùng sẽ nhập vào name, còn id tự động tăng
    // insert, delete, update là câu modify, dùng executeUpdate();
    public static boolean insertDerpartment(String newName) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành thêm mới department
            String sql = "insert into department (department_name) values (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Phong ban " + newName);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            if (c > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }


    // procedure
    // CallableStatement dùng để call procedure
    public static boolean insertDepartmentProcedure(String newName) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành thêm mới department bằng  Procedure insert_department()
            String sql = "{CALL insert_department(?)}";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, newName);

            int c = callableStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            if (c > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }

    // xóa phong ban theo tên
    public static boolean deleteDerpartment(String deleteName) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành xóa department
            String sql = "delete from department where department_name like ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, deleteName);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            if (c > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }

    // update phòng ban theo id
    // nhập vào id phòng ban cần sửa: 1
    // nhập ten phòng ban muốn sửa: MARKETING_UPDATE
    public static boolean updateDepartment(int id, String updateName) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành update department
            String sql = "update department set department_name = ? where department_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updateName);
            preparedStatement.setInt(2, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            if (c > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }

    // CRUD department      (CREATE     READ        UPDATE      DELETE)  department


    // ko dc dừng chương trình mà hãy suwar logic thêm mới cho tôi
    // VD: dev -> trong DB phai lưu là 'phòng ban dev'
    // VD: test -> trong DB phai lưu là 'phòng ban test'

    public static List<Department> findDepartmentTheMostEmployee() {
        List<Department> departments = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select po.*\n" +
                    "from position po\n" +
                    "         join account acc on po.position_id = acc.position_id\n" +
                    "group by po.position_id\n" +
                    "having count(po.position_id) = (select count(po.position_id)\n" +
                    "                                  from account\n" +
                    "                                  group by po.position_id\n" +
                    "                                  order by count(po.position_id) desc\n" +
                    "                                  limit 1);";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs

            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ column department_id
                String name = rs.getString("department_name");//lấy giá trị từ column department_name

                Department de = new Department(id, name);
                departments.add(de);
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return departments;
    }

}
