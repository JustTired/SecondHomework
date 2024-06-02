package JDBC.servlets;


import JDBC.dto.ProjectDto;
import JDBC.services.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

@WebServlet("/update-project")
public class UpdateProjectServlet extends HttpServlet {
    private static final ProjectService INSTANCE = ProjectService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/updateProject.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        var map = request.getParameterMap();
        INSTANCE.updateProject(new ProjectDto(
                Arrays.toString(map.get("name")),
                Date.valueOf(Arrays.toString(map.get("date"))))
        );
        try {
            response.getWriter().write("Project updated successfully");
        } catch (IOException e) {
            throw new RuntimeException("Error writing response", e);
        }
    }
}
