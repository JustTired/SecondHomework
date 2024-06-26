package JDBC.servlets;

import JDBC.dto.CompanyDto;
import JDBC.services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-company")
public class DeleteCompanyServlet extends HttpServlet {
    private static final CompanyService INSTANCE = CompanyService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/removeCompany.jsp")
                .forward(req, resp);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        boolean res = INSTANCE.deleteCompany(
                new CompanyDto(req.getParameter("companyName"))
        );
        var writer = resp.getWriter();
        if (res) {
            writer.write("Company deleted.");
        } else {
            writer.write("Company could not be deleted.");
        }
        writer.close();
    }
}
