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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/updateCompany.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String companyName = req.getParameter("companyName");
        String identify = req.getParameter("identify");
        resp.setContentType("text/html;charset=UTF-8");
        boolean result = INSTANCE.updateCompany(identify, new CompanyDto(companyName));
        var writer = resp.getWriter();
        if (result) {
            writer.println("Company updated successfully!");
        } else {
            writer.println("Company could not be updated!");
        }
        writer.close();
    }
}
