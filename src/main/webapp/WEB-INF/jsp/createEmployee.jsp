<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html lang="en">
<head>
    <title>create employee</title>
</head>
<body>
<form action="/create-employee" method="post">
    <H3>Create Employee</H3>
    <br>
    <label for="employeeUUID"> UUID:
        <input type="text" name="uuid" id="employeeUUID">
    </label><br>
    <label for="employeeFirstName"> First name:
        <input type="text" name="firstName" id="employeeFirstName">
    </label><br>
    <label for="employeeRole"> Role:
        <input type="text" name="role" id="employeeRole">
    </label><br>
    <label for="employeeEmail"> Email:
        <input type="text" name="email" id="employeeEmail">
    </label><br>
    <label for="employeeCompanyName"> Company Name:
        <input type="text" name="companyName" id="employeeCompanyName">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
