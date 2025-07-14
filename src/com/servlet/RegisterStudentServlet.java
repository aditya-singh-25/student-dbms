package com.servlet;

import com.util.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class RegisterStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        int rollNo = Integer.parseInt(request.getParameter("rollNo"));

        try (Connection con = DbUtil.getConnection()) {
            String sql = "INSERT INTO users (username, password, role, name, email, roll_no, course) VALUES (?, ?, 'student', ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, email);
            ps.setInt(5, rollNo);
            ps.setString(6, course);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("adminDashboard.html");
            } else {
                response.getWriter().println("<h3>❌ Failed to register student</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>❌ Error: " + e.getMessage() + "</h3>");
        }
    }
}
