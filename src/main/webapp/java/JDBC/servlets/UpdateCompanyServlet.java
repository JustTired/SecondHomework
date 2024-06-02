package JDBC.servlets;

import JDBC.dto.CompanyDto;
import JDBC.services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-company")
public class UpdateCompanyServlet extends HttpServlet {
    private static final CompanyService INSTANCE = CompanyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/updateCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String companyName = req.getParameter("companyName");
        INSTANCE.updateCompany(new CompanyDto(companyName));
        resp.getWriter().write("<H3>Company updated successfully</H3>");
    }
}
