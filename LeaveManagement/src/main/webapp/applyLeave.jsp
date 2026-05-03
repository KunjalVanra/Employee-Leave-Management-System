<link rel="stylesheet" href="style.css">

<div class="navbar">
    <a href="dashboard.jsp">Dashboard</a>
</div>

<div class="container">
    <h2>Apply Leave</h2>

    <form action="ApplyLeaveServlet" method="post">
        <input type="hidden" name="empId"
            value="<%= session.getAttribute("id") %>">

        <label>Start Date</label>
        <input type="date" name="start" required><br> <br>

        <label>End Date</label>
        <input type="date" name="end" required><br><br>

        <label>Reason</label>
        <textarea name="reason" required></textarea><br><br>

        <input type="submit" value="Apply Leave">
    </form>
</div>