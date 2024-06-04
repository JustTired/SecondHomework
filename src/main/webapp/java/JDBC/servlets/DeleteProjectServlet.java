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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/removeProject.jsp").forward(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        var map = req.getParameterMap();
        boolean res = INSTANCE.deleteProject(Arrays.toString(map.get("projectName")));
        var writer = resp.getWriter();
        if (res) {
            writer.write("Project deleted");
        } else {
            writer.write("Project not deleted");
        }
        writer.close();
    }
}
