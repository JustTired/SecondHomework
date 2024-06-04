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

@WebServlet("/update-project")
public class UpdateProjectServlet extends HttpServlet {
    private static final ProjectService INSTANCE = ProjectService.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/updateProject.jsp")
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean res = INSTANCE.updateProject(new ProjectDto(
                request.getParameter("name"),
                Date.valueOf(request.getParameter("date")))
        );
        var writer = response.getWriter();
        if (res) {
            writer.write("Project updated successfully");
        } else {
            writer.write("Project update failed");
        }
        writer.close();
    }
}
