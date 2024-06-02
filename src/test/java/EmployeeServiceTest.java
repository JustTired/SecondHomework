import JDBC.dto.EmployeeDto;
import JDBC.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    @Mock
    EmployeeService instance = EmployeeService.getInstance();
    @Mock
    EmployeeDto testDto = new EmployeeDto(UUID.randomUUID(),
            "Donald",
            "DevOps",
            "some@gmail.com",
            "Unknown"
    );
    @Mock
    List<?> listOfEmployees = instance.getAllEmployees();

    @Test
    void tests() {
        assertTrue(instance.addEmployee(testDto));
        assertTrue(instance.removeEmployee(testDto.uuid()));
        assertEquals(listOfEmployees.get(0), instance.getFirstEmployee());
    }
}
