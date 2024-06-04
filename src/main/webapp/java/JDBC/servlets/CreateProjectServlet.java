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

@WebServlet("/create-project")
public class CreateProjectServlet extends HttpServlet {
    private static final ProjectService INSTANCE = ProjectService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/createProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var map = req.getParameterMap();
        boolean res = INSTANCE.addProject(new ProjectDto(
                Arrays.toString(map.get("name")),
                Date.valueOf(Arrays.toString(map.get("date"))))
        );
        var writer = resp.getWriter();
        if (res) {
            writer.write("Project created successfully");
        } else {
            writer.write("Project already exists");
        }
        writer.close();
    }
}
