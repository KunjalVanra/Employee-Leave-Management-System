package servlet;

import dao.LeaveDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");

        new LeaveDAO().updateStatus(id, status);

        res.sendRedirect("admin.jsp");
    }
}