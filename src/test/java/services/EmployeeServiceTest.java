package services;

import JDBC.dto.EmployeeDto;
import JDBC.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeService instance;


    private EmployeeDto testDto;

    @BeforeEach
    void setUp() {
        testDto = mock(EmployeeDto.class);
        doReturn(randomUUID()).when(testDto).uuid();
        doReturn("Unknown").when(testDto).companyName();
        doReturn("Manager").when(testDto).role();
        doReturn("test@gmail.com").when(testDto).email();
        doReturn("Kate").when(testDto).firstName();
        instance = mock(EmployeeService.class);
        when(instance.addEmployee(any(EmployeeDto.class))).thenReturn(true);
        when(instance.removeEmployee(any(UUID.class))).thenReturn(true);
        when(instance.getFirstEmployee()).thenReturn(testDto);
        when(instance.getAllEmployees()).thenReturn(new ArrayList<>() {{
            add(testDto);
        }});
    }

    @Test
    void tests() {
        assertTrue(instance.addEmployee(testDto));
        assertEquals(instance.getFirstEmployee().uuid(), instance.getAllEmployees().get(0).uuid());
        assertTrue(instance.removeEmployee(testDto.uuid()));
    }
}
