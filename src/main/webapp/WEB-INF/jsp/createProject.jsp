<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html lang="en">
<head>
    <title>create project</title>
</head>
<body>
<form action="/create-project" method="post">
    <H3>Create Project</H3>
    <br>
    <label for="projectName"> Project name:
        <input type="text" name="name" id="projectName">
    </label><br>
    <label for="startDate"> Start date:
        <input type="date" name="date" id="startDate">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
