package dao;

import JDBC.dao.CompanyDao;
import JDBC.entities.CompanyEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyDaoTest {

    private CompanyDao dao;
    private final CompanyEntity testEntity = new CompanyEntity("test");

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
        dao = CompanyDao.getInstance();
    }

    @Test
    void tests() {
        assertEquals(dao.add(testEntity).getName(), testEntity.getName());
        assertEquals(dao.readAll().get(0).getName(), dao.readFirst().getName());
        assertTrue(dao.delete(testEntity.getName()));
    }

}
