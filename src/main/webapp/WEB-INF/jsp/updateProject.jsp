<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html lang="en">
<head>
    <title>update project</title>
</head>
<body>
<form action="/update-project" method="post">
    <H3>Update Company</H3>
    <br>
    <label for="projectName"> Project Name:
        <input type="text" name="projectName" id="projectName">
    </label><br>
    <label for="startDate"> Start project date:
        <input type="date" name="startDate" id="startDate">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>