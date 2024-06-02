import JDBC.dto.EmployeeDto;
import JDBC.services.EmployeeService;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeServiceTest {
    EmployeeService employee = EmployeeService.getInstance();
    EmployeeDto testEmployee = new EmployeeDto(
            UUID.randomUUID(),
            "Lara",
            "Developer",
            "some@gmail.com",
            "Unknown"
    );
    EmployeeDto first = employee.getFirstEmployee();


    @Test
    void shouldGetEmployee() {
        assertTrue(employee.addEmployee(testEmployee));
        assertEquals(first, employee.getFirstEmployee());
        assertTrue(employee.removeEmployee(testEmployee));
    }
}
