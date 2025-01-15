<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>List of Users</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <!-- Dynamic rows from database -->
        <tr>
            <td>1</td>
            <td>John Doe</td>
            <td>john@example.com</td>
            <td>Admin</td>
            <td><a href="edit-user?id=1">Edit</a> | <a href="delete-user?id=1">Delete</a></td>
        </tr>
    </table>
</body>
</html>
