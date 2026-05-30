package com.shop.backend.repository.impl;

import com.shop.backend.repository.IUserRepository;
import com.shop.entity.User;
import com.shop.entity.Admin;
import com.shop.entity.Employee;
import com.shop.enums.Role;
import com.shop.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements IUserRepository {
    @Override
    public List<User> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from users order by id desc;";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                Long id = rs.getLong("id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Role role = Role.valueOf(rs.getString("role"));
                if (role == Role.ADMIN) {
                    int expInYear = rs.getInt("exp_in_year");
                    User admin = new Admin(id, fullName, email, password, role, expInYear);
                    users.add(admin);
                } else if (role == Role.EMPLOYEE) {
                    String proSkill = rs.getString("pro_skill");
                    User employee = new Employee(id, fullName, email, password, role, proSkill);
                    users.add(employee);
                }
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from users where id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Role role = Role.valueOf(rs.getString("role"));
                if (role == Role.ADMIN) {
                    int expInYear = rs.getInt("exp_in_year");
                    user = new Admin(id, fullName, email, password, role, expInYear);
                } else if (role == Role.EMPLOYEE) {
                    String proSkill = rs.getString("pro_skill");
                    user = new Employee(id, fullName, email, password, role, proSkill);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection  = null;
        PreparedStatement preparedStatement = null;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: tiến hành xóa department
            String sql = "delete from users where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            int c = preparedStatement.executeUpdate();
            return c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        } finally {
            JDBCUtils.close(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public User login(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from users where email = ? and password = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            rs = statement.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String fullName = rs.getString("full_name");
                Role role = Role.valueOf(rs.getString("role"));
                if (role == Role.ADMIN) {
                    int expInYear = rs.getInt("exp_in_year");
                    user = new Admin(id, fullName, email, password, role, expInYear);
                } else if (role == Role.EMPLOYEE) {
                    String proSkill = rs.getString("pro_skill");
                    user = new Employee(id, fullName, email, password, role, proSkill);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }
        return user;
    }

    @Override
    public boolean create(String fullName, String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: tiến hành thêm mới department
            String sql = "INSERT INTO users (full_name, email, password, role) VALUES (?, ?, '123456', 'EMPLOYEE');";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);

            int c = preparedStatement.executeUpdate();
            return c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        } finally {
            JDBCUtils.close(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean checkExist = false;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from users where email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            rs = statement.executeQuery();
            if (rs.next()) {
                checkExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }
        return checkExist;
    }
}
