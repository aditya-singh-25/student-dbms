package com.servlet;

import com.dal.StudentDAL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DeleteStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        boolean status = StudentDAL.deleteStudent(username);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (status) {
            response.sendRedirect("viewStudents");
        } else {
            out.println("<h2>❌ Failed to delete student.</h2>");
        }
    }
}
