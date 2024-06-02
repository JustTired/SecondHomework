package JDBC.services;


import JDBC.dao.CompanyDao;
import JDBC.dto.CompanyDto;
import JDBC.entities.CompanyEntity;


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

    private CompanyEntity create(CompanyDto dto) {
        return new CompanyEntity(dto.name());
    }

    public boolean updateCompany(CompanyDto company) {
        return dao.update(company.name(), create(company));
    }
}
