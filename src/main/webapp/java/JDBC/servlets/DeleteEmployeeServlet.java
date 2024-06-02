package JDBC.servlets;

import JDBC.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/delete-employee")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final EmployeeService INSTANCE = EmployeeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-www-form-urlencoded");
        req.getRequestDispatcher("WEB-INF/jsp/removeEmployee.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        try {
            INSTANCE.removeEmployee(uuid);
            resp.getWriter().println("Employee deleted successfully");
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete employee");
        }
    }
}