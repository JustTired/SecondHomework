package JDBC.servlets;


import JDBC.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/employee")
public class FirstEmployeeServlet extends HttpServlet {
    private static final EmployeeService INSTANCE = EmployeeService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.write("<H3>Первый сотрудник</H3>");
            out.write("<ul>");
            out.write(String.valueOf(INSTANCE.getFirstEmployee()));
            out.write("</ul>");
        }
    }
}

