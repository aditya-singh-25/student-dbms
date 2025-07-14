package com.dal;

import java.sql.*;
import java.util.*;
import com.pojo.Teacher;
import com.util.DbUtil;

public class TeacherDAL {

    public static boolean addTeacher(Teacher teacher) {
        boolean status = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO users (username, password, role, name, email, department) VALUES (?, ?, 'teacher', ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, teacher.getUsername());
            ps.setString(2, teacher.getPassword());
            ps.setString(3, teacher.getName());
            ps.setString(4, teacher.getEmail());
            ps.setString(5, teacher.getDepartment());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<Teacher> getAllTeachers() {
        List<Teacher> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE role = 'teacher'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setUsername(rs.getString("username"));
                t.setName(rs.getString("name"));
                t.setEmail(rs.getString("email"));
                t.setDepartment(rs.getString("department"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Teacher getTeacherByUsername(String username) {
        Teacher teacher = null;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND role='teacher'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setUsername(rs.getString("username"));
                teacher.setName(rs.getString("name"));
                teacher.setEmail(rs.getString("email"));
                teacher.setDepartment(rs.getString("department"));
                teacher.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public static boolean updateTeacher(Teacher teacher) {
        boolean status = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "UPDATE users SET password=?, name=?, email=?, department=? WHERE username=? AND role='teacher'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, teacher.getPassword());
            ps.setString(2, teacher.getName());
            ps.setString(3, teacher.getEmail());
            ps.setString(4, teacher.getDepartment());
            ps.setString(5, teacher.getUsername());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean deleteTeacher(String username) {
        boolean status = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "DELETE FROM users WHERE username=? AND role='teacher'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
