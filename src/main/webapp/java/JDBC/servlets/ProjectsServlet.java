package JDBC.servlets;

import JDBC.services.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private static final ProjectService INSTANCE = ProjectService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.write("<H3>Список проектов</H3>");
            INSTANCE.getAllProjects().forEach(projectDto -> {
                out.write("""
                        <li>
                        %s %s
                        </li>
                        """.formatted(projectDto.name(),
                        projectDto.startDate().toString())
                );
            });
        }
    }
}
