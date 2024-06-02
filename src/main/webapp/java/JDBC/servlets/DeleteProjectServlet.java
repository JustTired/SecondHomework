package JDBC.servlets;

import JDBC.services.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/delete-project")
public class DeleteProjectServlet extends HttpServlet {
    private static final ProjectService INSTANCE = ProjectService.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/removeProject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var map = req.getParameterMap();
        INSTANCE.deleteProject(Arrays.toString(map.get("projectName")));
        try {
            resp.getWriter().write("Project deleted.");
        } catch (IOException e) {
            throw new RuntimeException("Error writing response", e);
        }
    }
}
