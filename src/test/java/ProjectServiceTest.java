import JDBC.dto.ProjectDto;
import JDBC.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {
    @Mock
    ProjectService instance = ProjectService.getInstance();
    @Mock
    ProjectDto testDto = new ProjectDto("test", Date.valueOf("2024-06-09"));
    @Mock
    List<?> listOfProjects = instance.getAllProjects();

    @Test
    void tests() {
        assertTrue(instance.addProject(testDto));
        assertTrue(instance.deleteProject(testDto.name()));
        assertEquals(listOfProjects.get(0), instance.getFirstProject());
    }
}
