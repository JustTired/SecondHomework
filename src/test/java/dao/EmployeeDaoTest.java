package dao;

import JDBC.dao.EmployeeDao;
import JDBC.entities.CompanyEntity;
import JDBC.entities.EmployeeEntity;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDaoTest {
    private EmployeeDao dao;
    private EmployeeEntity testEmployee;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16.3"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        dao = EmployeeDao.getInstance();
        testEmployee = new EmployeeEntity(
                UUID.randomUUID(),
                "Lara",
                "Conor",
                "Developer",
                "some@gmail.com",
                new CompanyEntity("Unknown")
        );
    }


    @Test
    void shouldGetEmployee() {
        assertEquals(dao.add(testEmployee).getUuid(), testEmployee.getUuid());
        assertEquals(dao.readAll().get(0).getUuid(), dao.readFirst().getUuid());
        assertTrue(dao.delete(testEmployee.getUuid()));
    }
}
