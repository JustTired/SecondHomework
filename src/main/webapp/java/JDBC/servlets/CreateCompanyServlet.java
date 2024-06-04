package JDBC.servlets;

import JDBC.dto.CompanyDto;
import JDBC.services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-company")
public class CreateCompanyServlet extends HttpServlet {
    private static final CompanyService INSTANCE = CompanyService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/createCompany.jsp")
                .forward(req, resp);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String companyName = req.getParameter("name");
        boolean result = INSTANCE.addCompany(new CompanyDto(companyName));
        var writer = resp.getWriter();
        if (result) {
            writer.write("Company created");
        } else {
            writer.write("Company already exists");
        }
        writer.close();
    }
}
