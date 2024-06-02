<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html lang="en">
<head>
    <title>remove employee</title>
</head>
<body>
<form action="/delete-employee" method="post">
    <H3>Delete Employee</H3>
    <br>
    <label for="employeeUUID"> UUID:
        <input type="text" name="uuid" id="employeeUUID">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
