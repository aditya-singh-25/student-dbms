package com.dal;

import java.sql.*;
import java.util.*;
import com.pojo.Student;
import com.util.DbUtil;

public class StudentDAL {

    public static boolean addStudent(Student student) {
        boolean status = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO users (username, password, role, name, email, roll_no, course) VALUES (?, ?, 'student', ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getUsername());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getName());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getRollNo());
            ps.setString(6, student.getCourse());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE role = 'student'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student s = new Student();
                s.setUsername(rs.getString("username"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setRollNo(rs.getInt("roll_no"));
                s.setCourse(rs.getString("course"));
                s.setPassword(rs.getString("password"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Student getStudentByUsername(String username) {
        Student student = null;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND role='student'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setUsername(rs.getString("username"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setRollNo(rs.getInt("roll_no"));
                student.setCourse(rs.getString("course"));
                student.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public static boolean updateStudent(Student student) {
        boolean status = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "UPDATE users SET password=?, name=?, email=?, roll_no=?, course=? WHERE username=? AND role='student'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getPassword());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getRollNo());
            ps.setString(5, student.getCourse());
            ps.setString(6, student.getUsername());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean deleteStudent(String username) {
        boolean status = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "DELETE FROM users WHERE username=? AND role='student'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
