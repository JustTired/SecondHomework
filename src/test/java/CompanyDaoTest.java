import JDBC.dao.CompanyDao;
import JDBC.entities.CompanyEntity;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyDaoTest {
    CompanyDao INSTANCE = CompanyDao.getInstance();
    CompanyEntity testEntity = new CompanyEntity("test");
    CompanyEntity firstEntity = INSTANCE.readFirst();

    @Test
    void shouldGetCompany() {
        assertEquals(INSTANCE.add(testEntity).getName(), testEntity.getName());
        assertEquals(firstEntity.getName(), INSTANCE.readFirst().getName());
        assertTrue(INSTANCE.delete(testEntity.getName()));
    }
}
