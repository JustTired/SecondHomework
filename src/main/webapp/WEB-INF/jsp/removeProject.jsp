<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html lang="en">
<head>
    <title>remove company</title>
</head>
<body>
<form action="/delete-project" method="post">
    <H3>Remove Company</H3>
    <br>
    <label for="projectName"> Project Name:
        <input type="text" name="projectName" id="projectName">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
