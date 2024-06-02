package JDBC.servlets;

import JDBC.services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/first-company")
public class FirstCompanyServlet extends HttpServlet {

    private static final CompanyService INSTANCE = CompanyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<H3>Первая компания</H3><br>");
            out.write("<ul>");
            out.write(INSTANCE.readFirstCompany().toString());
            out.write("</ul>");
            out.flush();
        }
    }
}
