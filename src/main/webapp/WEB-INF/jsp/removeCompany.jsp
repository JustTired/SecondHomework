<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html lang="en">
<head>
    <title>remove company</title>
</head>
<body>
<form action="/delete-company" method="post">
    <H3>Delete Company</H3>
    <br>
    <label for="companyName"> Company name:
        <input type="text" name="companyName" id="companyName">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>