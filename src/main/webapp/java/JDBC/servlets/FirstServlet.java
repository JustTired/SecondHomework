package JDBC.servlets;


import JDBC.services.EmployeeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/employee")
public class FirstServlet extends HttpServlet {
    private static final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.write("<H1>Первый сотрудник</H1>");
            out.write("<ul>");
            out.write(String.valueOf(employeeService.getFirstEmployee()));
            out.write("</ul>");
        }
    }
}

