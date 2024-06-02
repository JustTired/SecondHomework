import JDBC.dao.EmployeeDao;
import JDBC.entities.CompanyEntity;
import JDBC.entities.EmployeeEntity;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDaoTest {
    EmployeeDao INSTANCE = EmployeeDao.getInstance();
    EmployeeEntity testEmployee = new EmployeeEntity(
            UUID.randomUUID(),
            "Lara",
            "Conor",
            "Developer",
            "some@gmail.com",
            new CompanyEntity("Unknown")
    );
    EmployeeEntity firstEntity = INSTANCE.readFirst();


    @Test
    void shouldGetEmployee() {
        assertEquals(INSTANCE.add(testEmployee).getUuid(), testEmployee.getUuid());
        assertEquals(firstEntity, INSTANCE.readFirst());
        assertTrue(INSTANCE.delete(testEmployee.getUuid()));
    }
}
