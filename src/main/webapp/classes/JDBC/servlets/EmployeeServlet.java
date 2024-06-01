package JDBC.servlets;

import JDBC.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/entities")
public class EmployeeServlet extends HttpServlet {
    private final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.write("<H1>Список сотрудников</H1>");
            out.write("<ul>");
            employeeService.getAllEmployees().forEach(flightDto -> {
                out.write("""
                        <li>
                            %s %s %s %s
                        </li>
                        """.formatted(flightDto.getUuid().toString(), flightDto.getFirstName(), flightDto.getRole(), flightDto.getEmail())
                );
            });
            out.write("</ul>");
        }
    }
}
