package dao;

import JDBC.dao.ProjectDao;
import JDBC.entities.ProjectEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {
    private ProjectDao dao;
    private ProjectEntity testEntity;

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
        dao = ProjectDao.getInstance();
        testEntity = new ProjectEntity(
                "test",
                Date.valueOf("2023-09-11")
        );
    }

    @Test
    void shouldGetEmployee() {
        assertEquals(dao.add(testEntity).getName(), testEntity.getName());
        assertEquals(dao.readFirst().getName(), dao.readAll().get(0).getName());
        assertTrue(dao.delete(testEntity.getName()));
    }
}
