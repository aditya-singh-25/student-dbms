package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // current session
        if (session != null) {
            session.invalidate(); // logout
        }
        response.sendRedirect("index.html"); // after logout, go to home/login page
    }
}
