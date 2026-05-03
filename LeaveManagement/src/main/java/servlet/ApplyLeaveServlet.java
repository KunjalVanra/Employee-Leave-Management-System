package servlet;

import dao.LeaveDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ApplyLeaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int empId = Integer.parseInt(req.getParameter("empId"));
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        String reason = req.getParameter("reason");

        new LeaveDAO().applyLeave(empId, start, end, reason);

        res.sendRedirect("dashboard.jsp");
    }
}