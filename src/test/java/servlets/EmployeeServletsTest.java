package servlets;

import JDBC.servlets.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

class EmployeeServletsTest {
    private static final String createPath = "WEB-INF/jsp/createEmployee.jsp";
    private static final String updatePath = "WEB-INF/jsp/updateEmployee.jsp";
    private static final String deletePath = "WEB-INF/jsp/removeEmployee.jsp";

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;
    private PrintWriter writer;
    private EmployeesServlet allEmployees;
    private FirstEmployeeServlet firstEmployee;
    private CreateEmployeeServlet createEmployee;
    private UpdateEmployeeServlet updateEmployee;
    private DeleteEmployeeServlet deleteEmployee;

    @BeforeEach
    void setUp() {
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        writer = mock(PrintWriter.class);
    }

    @SneakyThrows
    @Test
    void shouldReadFirstEmployee() {
        firstEmployee = new FirstEmployeeServlet();
        when(resp.getWriter()).thenReturn(writer);
        firstEmployee.doGet(req, resp);

        verify(resp, times(1)).getWriter();
        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldReadAllEmployees() {
        allEmployees = new EmployeesServlet();
        when(resp.getWriter()).thenReturn(writer);
        allEmployees.doGet(req, resp);

        verify(resp, times(1)).getWriter();
        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldCreateEmployeeGet() {
        createEmployee = new CreateEmployeeServlet();
        when(req.getRequestDispatcher(createPath)).thenReturn(dispatcher);
        createEmployee.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(createPath);
        verify(dispatcher).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldUpdateEmployeeGet() {
        updateEmployee = new UpdateEmployeeServlet();
        when(req.getRequestDispatcher(updatePath)).thenReturn(dispatcher);
        updateEmployee.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(updatePath);
        verify(dispatcher).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldUpdateEmployeePost() {
        updateEmployee = new UpdateEmployeeServlet();
        doReturn(randomUUID().toString()).when(req).getParameter("uuid");
        doReturn(writer).when(resp).getWriter();
        updateEmployee.doPost(req, resp);

        verify(resp, times(1)).getWriter();
        verify(req, times(1)).getParameter("uuid");
        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldDeleteEmployeeGet() {
        deleteEmployee = new DeleteEmployeeServlet();
        when(req.getRequestDispatcher(deletePath)).thenReturn(dispatcher);
        deleteEmployee.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(deletePath);
        verify(dispatcher).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldDeleteEmployeeDelete() {
        deleteEmployee = new DeleteEmployeeServlet();
        doReturn(randomUUID().toString()).when(req).getParameter("uuid");
        doReturn(writer).when(resp).getWriter();
        deleteEmployee.doDelete(req, resp);

        verify(resp, times(1)).getWriter();
        verify(writer, times(1)).write(anyString());
        verify(writer, times(1)).close();
    }
}
