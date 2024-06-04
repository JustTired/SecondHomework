package servlets;

import JDBC.dto.ProjectDto;
import JDBC.servlets.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

class ProjectServletsTest {
    private static final String createPath = "WEB-INF/jsp/createProject.jsp";
    private static final String updatePath = "/WEB-INF/jsp/updateProject.jsp";
    private static final String deletePath = "WEB-INF/jsp/removeProject.jsp";

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;
    private PrintWriter writer;
    private FirstProjectServlet firstProject;
    private ProjectsServlet allProjects;
    private CreateProjectServlet createProject;
    private UpdateProjectServlet updateProject;
    private DeleteProjectServlet deleteProject;

    @BeforeEach
    void setUp() {
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        writer = mock(PrintWriter.class);
    }

    @SneakyThrows
    @Test
    void shouldReadFirstCompany() {
        firstProject = new FirstProjectServlet();
        when(resp.getWriter()).thenReturn(writer);
        firstProject.doGet(req, resp);

        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldReadAllCompanies() {
        allProjects = new ProjectsServlet();
        when(resp.getWriter()).thenReturn(writer);
        allProjects.doGet(req, resp);

        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldCreateProjectGet() {
        createProject = new CreateProjectServlet();
        when(req.getRequestDispatcher(createPath)).thenReturn(dispatcher);
        createProject.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(createPath);
        verify(dispatcher, times(1)).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldUpdateProjectGet() {
        updateProject = new UpdateProjectServlet();
        when(req.getRequestDispatcher(updatePath)).thenReturn(dispatcher);
        updateProject.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(updatePath);
        verify(dispatcher, times(1)).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldUpdateProjectPost() {
        updateProject = new UpdateProjectServlet();
        when(resp.getWriter()).thenReturn(writer);
        when(req.getParameter("name")).thenReturn("testName");
        when(req.getParameter("date")).thenReturn("2022-02-02");
        updateProject.doPost(req, resp);

        verify(req, times(1)).getParameter("name");
        verify(req, times(1)).getParameter("date");
        verify(writer, times(1)).write(anyString());
        verify(writer, times(1)).close();
    }

    @SneakyThrows
    @Test
    void shouldDeleteProjectGet() {
        deleteProject = new DeleteProjectServlet();
        when(req.getRequestDispatcher(deletePath)).thenReturn(dispatcher);
        deleteProject.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher(deletePath);
        verify(dispatcher, times(1)).forward(req, resp);
    }

    @SneakyThrows
    @Test
    void shouldDeleteProjectDelete() {
        deleteProject = new DeleteProjectServlet();
        doReturn(writer).when(resp).getWriter();
        deleteProject.doDelete(req, resp);

        verify(resp, times(1)).getWriter();
        verify(writer, times(1)).write(anyString());
        verify(writer, times(1)).close();
    }
}
