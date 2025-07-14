package com.servlet;

import com.util.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                switch (role) {
                    case "admin":
                        res.sendRedirect("adminDashboard.html");
                        break;
                    case "teacher":
                        res.sendRedirect("teacherDashboard.html");
                        break;
                    case "student":
                        res.sendRedirect("studentDashboard.html");
                        break;
                    default:
                        res.sendRedirect("login.html?error=Unknown%20role");
                }
            } else {
                res.sendRedirect("login.html?error=Invalid%20credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("login.html?error=Server%20error");
        }
    }
}
