<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html lang="en">
<head>
    <title>update company</title>
</head>
<body>
<form action="/update-company" method="post">
    <H3>Update Company</H3>
    <label for="companyName"> Company Name:
        <input type="text" name="companyName" id="companyName">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>