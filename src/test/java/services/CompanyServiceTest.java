package services;

import JDBC.dto.CompanyDto;
import JDBC.services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    private CompanyService instance;
    private CompanyDto testDto;

    @BeforeEach
    void setUp() {
        testDto = mock(CompanyDto.class);
        doReturn("Google").when(testDto).name();
        instance = mock(CompanyService.class);
        when(instance.addCompany(any(CompanyDto.class))).thenReturn(true);
        when(instance.deleteCompany(any(CompanyDto.class))).thenReturn(true);
        when(instance.readFirstCompany()).thenReturn(testDto);
        when(instance.readAllCompanies()).thenReturn(
                new ArrayList<>() {{
                    add(testDto);
                }});
    }


    @Test
    void someTests() {
        assertTrue(instance.addCompany(testDto));
        assertTrue(instance.deleteCompany(testDto));
        assertEquals(instance.readAllCompanies().get(0).name(), instance.readFirstCompany().name());
    }
}
