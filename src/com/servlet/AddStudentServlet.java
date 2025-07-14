package com.servlet;

import com.pojo.Student;
import com.dal.StudentDAL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int rollNo = Integer.parseInt(request.getParameter("rollNo"));
        String course = request.getParameter("course");

        Student student = new Student(username, password, name, email, rollNo, course);
        boolean status = StudentDAL.addStudent(student);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (status) {
            response.sendRedirect("viewStudents");
        } else {
            out.println("<h2>❌ Failed to add student.</h2>");
        }
    }
}
