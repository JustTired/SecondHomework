package dao;

import JDBC.dao.ProjectDao;
import JDBC.entities.ProjectEntity;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {
    private final ProjectDao INSTANCE = ProjectDao.getInstance();
    private final ProjectEntity testEntity = new ProjectEntity("test", Date.valueOf("2023-09-11"));
    private final ProjectEntity testEntity2 = INSTANCE.readFirst();

    @Test
    void shouldGetEmployee() {
        assertEquals(INSTANCE.add(testEntity).getName(), testEntity.getName());
        assertEquals(INSTANCE.readFirst().getName(), testEntity2.getName());
        assertTrue(INSTANCE.delete(testEntity.getName()));
    }
}