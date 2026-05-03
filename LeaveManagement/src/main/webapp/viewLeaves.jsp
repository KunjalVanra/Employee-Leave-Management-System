<%@ page import="java.sql.*,dao.LeaveDAO" %>
<link rel="stylesheet" href="style.css">

<div class="navbar">
    <a href="dashboard.jsp">Dashboard</a>
    |
    <a href="LogoutServlet">Logout</a>
</div>

<h2 style="text-align:center;">My Leaves</h2>

<table>
<tr>
    <th>Start</th>
    <th>End</th>
    <th>Reason</th>
    <th>Status</th>
</tr>

<%
    int empId = (int) session.getAttribute("id");
    ResultSet rs = new LeaveDAO().getLeavesByEmployee(empId);

    while(rs.next()){
%>

<tr>
    <td><%= rs.getDate("start_date") %></td>
    <td><%= rs.getDate("end_date") %></td>
    <td><%= rs.getString("reason") %></td>
    <td>
        <b style="color:
            <%= rs.getString("status").equals("Approved") ? "green" :
                rs.getString("status").equals("Rejected") ? "red" : "orange" %>">
            <%= rs.getString("status") %>
        </b>
    </td>
</tr>

<% } %>
</table>