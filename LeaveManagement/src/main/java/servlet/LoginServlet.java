package servlet;

import dao.EmployeeDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String selectedRole = req.getParameter("role");

        try {
            EmployeeDAO dao = new EmployeeDAO();
            ResultSet rs = dao.login(email, password);

            if (rs != null && rs.next()) {

                String dbRole = rs.getString("role");

                // ✅ SAFE ROLE CHECK
                if (dbRole == null || selectedRole == null ||
                    !dbRole.trim().equalsIgnoreCase(selectedRole.trim())) {

                    res.sendRedirect("login.jsp?error=role");
                    return;
                }

                HttpSession session = req.getSession();
                session.setAttribute("id", rs.getInt("id"));
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("role", dbRole);

                if ("admin".equalsIgnoreCase(dbRole)) {
                    res.sendRedirect("admin.jsp");
                } else {
                    res.sendRedirect("dashboard.jsp");
                }

            } else {
                res.sendRedirect("login.jsp?error=invalid");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("login.jsp?error=invalid");
        }
    }
}