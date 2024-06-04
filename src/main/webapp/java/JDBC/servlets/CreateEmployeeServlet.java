package JDBC.servlets;

import JDBC.dto.EmployeeDto;
import JDBC.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@WebServlet("/create-employee")
public class CreateEmployeeServlet extends HttpServlet {
    private static final EmployeeService INSTANCE = EmployeeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/createEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var map = req.getParameterMap();
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        boolean res = INSTANCE.addEmployee(new EmployeeDto(
                uuid,
                Arrays.toString(map.get("firstName")),
                Arrays.toString(map.get("role")),
                Arrays.toString(map.get("email")),
                Arrays.toString(map.get("companyName")))
        );
        var writer = resp.getWriter();
        if (res) {
            writer.write("Employee has been created");
        } else {
            writer.write("Employee has not been created");
        }
        writer.close();
    }
}
