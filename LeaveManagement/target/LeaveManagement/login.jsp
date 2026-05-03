<link rel="stylesheet" href="style.css">

<div class="login-container">

    <div class="login-card">

        <h2>Leave Management</h2>
        <p>Login to continue</p>

        <% if("invalid".equals(request.getParameter("error"))){ %>
            <div class="error">Invalid Email or Password</div>
        <% } %>

        <% if("role".equals(request.getParameter("error"))){ %>
            <div class="error">Wrong Role Selected</div>
        <% } %>

        <form action="LoginServlet" method="post">

    <select name="role" required>
        <option value="">Select Role</option>
        <option value="admin">Admin</option>
        <option value="employee">Employee</option>
    </select>

    <input type="text" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>

    <input type="submit" value="Login">

</form>

    </div>

</div>