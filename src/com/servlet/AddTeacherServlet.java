package com.servlet;

import com.pojo.Teacher;
import com.dal.TeacherDAL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AddTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password"); // ✅ Add this line
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");

        Teacher teacher = new Teacher(username, password, name, email, department); // ✅ Constructor with password
        boolean status = TeacherDAL.addTeacher(teacher);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (status) {
            response.sendRedirect("viewTeachers");
        } else {
            out.println("<h2>❌ Failed to add teacher.</h2>");
        }
    }
}
