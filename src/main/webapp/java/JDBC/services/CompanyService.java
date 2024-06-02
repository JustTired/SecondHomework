package JDBC.services;


import JDBC.dao.CompanyDao;
import JDBC.dto.CompanyDto;


import java.util.List;


public class CompanyService {
    private static final CompanyService INSTANCE = new CompanyService();
    private static final CompanyDao dao = CompanyDao.getInstance();

    private CompanyService() {
    }

    public static CompanyService getInstance() {
        return INSTANCE;
    }

    public List<CompanyDto> readAllCompanies() {
        return dao.readAll().stream()
                .map(entity -> new CompanyDto(entity.getName()))
                .toList();
    }

    public CompanyDto readFirstCompany() {
        return new CompanyDto(
                dao.readFirst().getName()
        );
    }

}
