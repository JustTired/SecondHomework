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

    public boolean updateCompany(String identify, CompanyDto company) {
        return dao.update(identify, create(company));
    }

    public boolean deleteCompany(CompanyDto company) {
        return dao.delete(company.name());
    }

    public boolean createCompany(CompanyDto dto) {
        return dao.add(create(dto)).getName()
                .equals(dto.name());
    }
}
