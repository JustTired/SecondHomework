package servlets;

import JDBC.dto.CompanyDto;
import JDBC.servlets.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import static org.mockito.Mockito.*;

class CompanyServletsTest {
    private static final String createPath = "WEB-INF/jsp/createCompany.jsp";
    private static final String deletePath = "WEB-INF/jsp/removeCompany.jsp";
    private static final String updatePath = "WEB-INF/jsp/updateCompany.jsp";
    @Mock
    private PrintWriter writer;
    @InjectMocks
    private CompaniesServlet allCompanies;

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;
    private CreateCompanyServlet createCompany;
    private DeleteCompanyServlet deleteCompany;
    private UpdateCompanyServlet updateCompany;
    private FirstCompanyServlet firstCompany;
    private CompanyDto testDto;


    @BeforeEach
    void setUp() {
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        writer = mock(PrintWriter.class);
    }

    @SneakyThrows
    @Test
    void shouldAddCompanies() {
        allCompanies = new CompaniesServlet();
        when(resp.getWriter()).thenReturn(writer);
        allCompanies.doGet(req, resp);

        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldFirstCompany() {
        firstCompany = new FirstCompanyServlet();
        when(resp.getWriter()).thenReturn(writer);
        firstCompany.doGet(req, resp);

        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldCreateCompanyGet() {
        createCompany = new CreateCompanyServlet();
        when(req.getRequestDispatcher(createPath)).thenReturn(dispatcher);
        createCompany.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(createPath);
        verify(dispatcher).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldDeleteCompanyGet() {
        deleteCompany = new DeleteCompanyServlet();
        when(req.getRequestDispatcher(deletePath)).thenReturn(dispatcher);
        deleteCompany.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(deletePath);
        verify(dispatcher).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldDeleteCompanyDelete() {
        deleteCompany = new DeleteCompanyServlet();
        doReturn(writer).when(resp).getWriter();
        deleteCompany.doDelete(req, resp);

        verify(writer, times(1)).write(anyString());
        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldUpdateCompanyGet() {
        updateCompany = new UpdateCompanyServlet();
        when(req.getRequestDispatcher(updatePath)).thenReturn(dispatcher);
        updateCompany.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(updatePath);
        verify(dispatcher).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldUpdateCompanyPost() {
        updateCompany = new UpdateCompanyServlet();
        doReturn(writer).when(resp).getWriter();
        updateCompany.doPost(req, resp);

        verify(writer, times(1)).close();
    }
}