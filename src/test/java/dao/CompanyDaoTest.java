package dao;

import JDBC.dao.CompanyDao;
import JDBC.entities.CompanyEntity;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyDaoTest {
    private final CompanyDao INSTANCE = CompanyDao.getInstance();
    private final CompanyEntity testEntity = new CompanyEntity("test");
    private final CompanyEntity firstEntity = INSTANCE.readFirst();

    @Test
    void shouldGetCompany() {
        assertEquals(INSTANCE.add(testEntity).getName(), testEntity.getName());
        assertEquals(firstEntity.getName(), INSTANCE.readFirst().getName());
        assertTrue(INSTANCE.delete(testEntity.getName()));
    }
}
