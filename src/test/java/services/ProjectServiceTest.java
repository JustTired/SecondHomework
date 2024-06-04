package services;

import JDBC.dto.ProjectDto;
import JDBC.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    private ProjectService instance;
    private ProjectDto testDto;

    @BeforeEach
    void setUp() {
        testDto = mock(ProjectDto.class);
        doReturn("Amazon").when(testDto).name();
        doReturn(Date.valueOf("2024-06-09")).when(testDto).startDate();
        instance = mock(ProjectService.class);
        when(instance.addProject(any(ProjectDto.class))).thenReturn(true);
        when(instance.deleteProject(anyString())).thenReturn(true);
        when(instance.getFirstProject()).thenReturn(testDto);
        when(instance.getAllProjects()).thenReturn(new ArrayList<>() {{
            add(testDto);
        }});

    }

    @Test
    void tests() {
        assertTrue(instance.addProject(testDto));
        assertTrue(instance.deleteProject(testDto.name()));
        assertEquals(instance.getAllProjects().get(0), instance.getFirstProject());
    }
}
