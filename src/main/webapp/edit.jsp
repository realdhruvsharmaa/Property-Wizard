<html>
<head>
    <title>Edit Property</title>
</head>
<body>
    <h1>Edit Property</h1>
    <form action="update-property" method="post">
        <label for="propertyId">Property ID:</label>
        <input type="text" id="propertyId" name="propertyId" readonly><br>

        <label for="name">Property Name:</label>
        <input type="text" id="name" name="name"><br>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location"><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price"><br>

        <button type="submit">Update</button>
    </form>
</body>
</html>