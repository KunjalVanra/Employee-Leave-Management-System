<%@ page import="java.sql.*,dao.LeaveDAO" %>
<link rel="stylesheet" href="style.css">

<div class="navbar">
    <div>Admin Dashboard</div>
    <a href="LogoutServlet">Logout</a>
</div>

<h2 style="text-align:center;">Admin Dashboard</h2>

<%
    LeaveDAO dao = new LeaveDAO();
%>

<!-- ? COUNTERS -->
<div class="card-container">

    <div class="card">
        <h3>Pending</h3>
        <h2><%= dao.countPending() %></h2>
    </div>

    <div class="card">
        <h3>Approved</h3>
        <h2><%= dao.countApproved() %></h2>
    </div>

    <div class="card">
        <h3>Rejected</h3>
        <h2><%= dao.countRejected() %></h2>
    </div>

</div>

<!-- ? PENDING -->
<h3 style="text-align:center;">Pending Leaves</h3>

<%
    ResultSet p = dao.getPendingLeaves();
    while(p != null && p.next()){
%>

<div class="card">

    <b>Name:</b> <%= p.getString("name") %><br>
    <b>Reason:</b> <%= p.getString("reason") %><br>
    <b>From:</b> <%= p.getDate("start_date") %><br>
    <b>To:</b> <%= p.getDate("end_date") %><br>

    <form action="AdminServlet" method="post">
        <input type="hidden" name="id" value="<%= p.getInt("id") %>">

        <button name="status" value="Approved" style="background:green;color:white;">
            Approve
        </button>

        <button name="status" value="Rejected" style="background:red;color:white;">
            Reject
        </button>
    </form>

</div>

<hr>

<% } %>

<!-- ? APPROVED -->
<h3 style="text-align:center;">Approved Leaves</h3>

<%
    ResultSet a = dao.getApprovedLeaves();
    while(a != null && a.next()){
%>

<div class="card">
    <b>Name:</b> <%= a.getString("name") %><br>
    <b>Reason:</b> <%= a.getString("reason") %><br>
    <b>Status:</b> <span style="color:green;">Approved</span>
</div>

<hr>

<% } %>

<!-- ? HISTORY -->
<h3 style="text-align:center;">History</h3>

<%
    ResultSet h = dao.getAllLeaves();
    while(h != null && h.next()){
%>

<div class="card">
    <b>Name:</b> <%= h.getString("name") %><br>
    <b>Reason:</b> <%= h.getString("reason") %><br>

    <b>Status:</b>
    <span style="color:
        <%= h.getString("status").equals("Approved") ? "green" :
            h.getString("status").equals("Rejected") ? "red" : "orange" %>">

        <%= h.getString("status") %>

    </span>
</div>

<hr>

<% } %>