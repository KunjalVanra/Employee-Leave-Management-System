<link rel="stylesheet" href="style.css">

<div class="navbar">
    <div>Welcome ${sessionScope.name}</div>
    <div>
        <a href="LogoutServlet">Logout</a>
    </div>
</div>

<div class="card-container">

    <div class="card">
        <h3>Apply Leave</h3>
        <a href="applyLeave.jsp">
            <button>Apply</button>
        </a>
    </div>

    <div class="card">
        <h3>My Leaves</h3>
        <a href="viewLeaves.jsp">
            <button>View</button>
        </a>
    </div>

</div>