import JDBC.dto.CompanyDto;
import JDBC.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyServiceTest {
    @Mock
    CompanyService instance = CompanyService.getInstance();
    @Mock
    CompanyDto companyDto = new CompanyDto("test");
    @Mock
    List<?> list = instance.readAllCompanies();
    @Mock
    CompanyDto firstCompanyDto = instance.readFirstCompany();

    @Test
    void someTests() {
        assertTrue(instance.createCompany(companyDto));
        assertTrue(instance.deleteCompany(companyDto));
        assertEquals(list.get(0), firstCompanyDto);
    }
}
