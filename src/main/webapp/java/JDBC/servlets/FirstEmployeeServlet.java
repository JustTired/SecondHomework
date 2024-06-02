package JDBC.servlets;


import JDBC.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/employee")
public class FirstEmployeeServlet extends HttpServlet {
    private static final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("application/x-www-form-urlencoded");
//        resp.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = resp.getWriter()) {
//            out.write("<H1>Первый сотрудник</H1>");
//            out.write("<ul>");
//            out.write(String.valueOf(employeeService.getFirstEmployee()));
//            out.write("</ul>");
//        }
        req.setAttribute("employees", employeeService.getAllEmployees());
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req, resp);
    }
}

