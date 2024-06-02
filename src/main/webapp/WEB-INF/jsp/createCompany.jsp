<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html lang="en">
<head>
    <title>create company</title>
</head>
<body>
<form action="/create-company" method="post">
    <H3>Create Company</H3>
    <br>
    <label for="companyName"> Company name:
        <input type="text" name="name" id="companyName">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>