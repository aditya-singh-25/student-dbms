package com.servlet;

import com.dal.StudentDAL;
import com.pojo.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class ViewStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // ✅ Get role from session
        HttpSession session = request.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;

        String dashboard = "index.html"; // default
        if ("admin".equals(role)) {
            dashboard = "adminDashboard.html";
        } else if ("teacher".equals(role)) {
            dashboard = "teacherDashboard.html";
        } else if ("student".equals(role)) {
            dashboard = "studentDashboard.html";
        }

        List<Student> students = StudentDAL.getAllStudents();

        out.println("<html><head><title>All Students</title><style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background: linear-gradient(to right, #eef2f3, #8e9eab); margin: 0; padding: 40px 20px; }");
        out.println(".container { background-color: white; padding: 40px; border-radius: 12px; box-shadow: 0 12px 30px rgba(0,0,0,0.15); max-width: 1000px; margin: auto; }");
        out.println("h2 { color: #2a5885; text-align: center; font-size: 28px; margin-bottom: 30px; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 15px; }");
        out.println("th, td { padding: 12px 15px; border: 1px solid #ddd; text-align: center; font-size: 16px; }");
        out.println("th { background-color: #2a5885; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("tr:hover { background-color: #f1f1f1; }");
        out.println(".btn { padding: 6px 14px; background-color: #2a5885; color: white; border-radius: 6px; text-decoration: none; font-size: 14px; margin: 0 4px; }");
        out.println(".btn:hover { background-color: #174c78; }");
        out.println(".top-link { display: block; text-align: center; margin-top: 25px; }");
        out.println(".top-link a { font-weight: bold; color: #2a5885; text-decoration: none; }");
        out.println(".top-link a:hover { color: #174c78; }");
        out.println("</style></head><body>");
        
        out.println("<div class='container'>");
        out.println("<h2> All Students</h2>");

        if (students == null || students.isEmpty()) {
            out.println("<p style='text-align:center;'>No students found.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Username</th><th>Roll No</th><th>Name</th><th>Email</th><th>Course</th><th>Actions</th></tr>");
            for (Student s : students) {
                out.println("<tr>");
                out.println("<td>" + s.getUsername() + "</td>");
                out.println("<td>" + s.getRollNo() + "</td>");
                out.println("<td>" + s.getName() + "</td>");
                out.println("<td>" + s.getEmail() + "</td>");
                out.println("<td>" + s.getCourse() + "</td>");
                out.println("<td>");
                out.println("<a href='editStudent?username=" + s.getUsername() + "' class='btn'>Edit</a>");
                out.println("<a href='deleteStudent?username=" + s.getUsername() + "' class='btn' onclick='return confirm(\"Are you sure?\")'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        // ✅ Role-based back link
        out.println("<div class='top-link'><a href='" + dashboard + "'>Back to Dashboard</a></div>");
        out.println("</div></body></html>");
        out.close();
    }
}
