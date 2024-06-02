package JDBC.servlets;

import JDBC.services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private static final CompanyService INSTANCE = CompanyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.write("<H3>Список компаний</H3>");
            out.write("<ul>");
            INSTANCE.readAllCompanies().forEach(companyDto ->
                    out.write(
                            """
                                    <li>
                                    %s
                                    </li>
                                    """.formatted(companyDto.name())
                    ));
            out.write("</ul>");
        }
    }
}
