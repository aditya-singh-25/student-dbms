package com.servlet;

import com.dal.TeacherDAL;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DeleteTeacherServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        boolean status = TeacherDAL.deleteTeacher(username);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (status) {
            response.sendRedirect("viewTeachers");
        } else {
            out.println("<h2>❌ Failed to delete teacher.</h2>");
        }
    }
}
