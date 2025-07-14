package com.servlet;

import com.pojo.Student;
import com.dal.StudentDAL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class EditStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int rollNo = Integer.parseInt(request.getParameter("rollNo"));
        String course = request.getParameter("course");

        Student student = new Student(username, password, name, email, rollNo, course);
        boolean status = StudentDAL.updateStudent(student);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (status) {
            response.sendRedirect("viewStudents");
        } else {
            out.println("<h2>❌ Failed to update student.</h2>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        Student student = StudentDAL.getStudentByUsername(username);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (student != null) {
            out.println("<html><head><title>Edit Student</title><style>");
            out.println("body { font-family: Arial; padding: 40px; background: #f0f0f0; }");
            out.println(".form-container { background: #fff; padding: 30px; border-radius: 10px; width: 400px; margin: auto; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            out.println("h2 { text-align: center; color: #2a5885; }");
            out.println("input { width: 100%; padding: 10px; margin: 10px 0; border-radius: 6px; border: 1px solid #ccc; }");
            out.println("input[type='submit'] { background-color: #2a5885; color: white; border: none; font-weight: bold; }");
            out.println("input[type='submit']:hover { background-color: #174c78; cursor: pointer; }");
            out.println("</style></head><body>");

            out.println("<div class='form-container'>");
            out.println("<h2>Edit Student</h2>");
            out.println("<form method='post' action='editStudent'>");
            out.println("<input type='hidden' name='username' value='" + student.getUsername() + "'>");
            out.println("<input type='text' name='name' value='" + student.getName() + "' required>");
            out.println("<input type='email' name='email' value='" + student.getEmail() + "' required>");
            out.println("<input type='text' name='course' value='" + student.getCourse() + "' required>");
            out.println("<input type='number' name='rollNo' value='" + student.getRollNo() + "' required>");
            out.println("<input type='password' name='password' placeholder='New Password' value='" + student.getPassword() + "' required>");
            out.println("<input type='submit' value='Update Student'>");
            out.println("</form>");
            out.println("</div></body></html>");
        } else {
            out.println("<h2>Student not found!</h2>");
        }
        out.close();
    }
}
