<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h1>Add New User</h1>
    <form action="save-user" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br>

        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select><br>

        <button type="submit">Save</button>
    </form>
</body>
</html>