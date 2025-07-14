package com.servlet;

import com.pojo.Teacher;
import com.dal.TeacherDAL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class EditTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");

        Teacher teacher = new Teacher(username, password, name, email, department);
        boolean status = TeacherDAL.updateTeacher(teacher);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (status) {
            response.sendRedirect("viewTeachers");
        } else {
            out.println("<h2>❌ Failed to update teacher.</h2>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        Teacher teacher = TeacherDAL.getTeacherByUsername(username);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (teacher != null) {
            out.println("<html><head><title>Edit Teacher</title><style>");
            out.println("body { font-family: Arial; padding: 40px; background: #f0f0f0; }");
            out.println(".form-container { background: #fff; padding: 30px; border-radius: 10px; width: 400px; margin: auto; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            out.println("h2 { text-align: center; color: #2a5885; }");
            out.println("input { width: 100%; padding: 10px; margin: 10px 0; border-radius: 6px; border: 1px solid #ccc; }");
            out.println("input[type='submit'] { background-color: #2a5885; color: white; border: none; font-weight: bold; }");
            out.println("input[type='submit']:hover { background-color: #174c78; cursor: pointer; }");
            out.println("</style></head><body>");

            out.println("<div class='form-container'>");
            out.println("<h2>Edit Teacher</h2>");
            out.println("<form method='post' action='editTeacher'>");
            out.println("<input type='hidden' name='username' value='" + teacher.getUsername() + "'>");
            out.println("<input type='text' name='name' value='" + teacher.getName() + "' required>");
            out.println("<input type='email' name='email' value='" + teacher.getEmail() + "' required>");
            out.println("<input type='text' name='department' value='" + teacher.getDepartment() + "' required>");
            out.println("<input type='password' name='password' value='" + teacher.getPassword() + "' required>");
            out.println("<input type='submit' value='Update Teacher'>");
            out.println("</form>");
            out.println("</div></body></html>");
        } else {
            out.println("<h2>Teacher not found!</h2>");
        }
        out.close();
    }
}
