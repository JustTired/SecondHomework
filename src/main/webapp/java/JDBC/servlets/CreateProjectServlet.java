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

@WebServlet("/create-project")
public class CreateProjectServlet extends HttpServlet {
    private static final ProjectService INSTANCE = ProjectService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/createProject.jsp").forward(req, resp);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean res = INSTANCE.addProject(new ProjectDto(
                req.getParameter("name"),
                Date.valueOf(req.getParameter("date")))
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
