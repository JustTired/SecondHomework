package JDBC.services;

import JDBC.dao.EmployeeDao;
import JDBC.dto.EmployeeDto;
import JDBC.entities.CompanyEntity;
import JDBC.entities.EmployeeEntity;

import java.util.List;
import java.util.UUID;


public class EmployeeService {
    private static final EmployeeService INSTANCE = new EmployeeService();
    private final EmployeeDao employeeDao = EmployeeDao.getInstance();


    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        return INSTANCE;
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeDao.readAll().stream()
                .map(employeeEntity -> new EmployeeDto(
                        employeeEntity.getUuid(),
                        employeeEntity.getFirstName(),
                        employeeEntity.getRole(),
                        employeeEntity.getEmail(),
                        employeeEntity.getCompany().getName())
                ).toList();
    }

    public EmployeeDto getFirstEmployee() {
        var employee = employeeDao.readFirst();

        return new EmployeeDto(employee.getUuid(),
                employee.getFirstName(),
                employee.getRole(),
                employee.getEmail(),
                employee.getCompany().getName()
        );
    }

    private EmployeeEntity create(EmployeeDto employeeDto) {

        return new EmployeeEntity(employeeDto.uuid(),
                employeeDto.firstName(),
                null,
                employeeDto.role(),
                employeeDto.email(),
                new CompanyEntity(employeeDto.companyName())
        );
    }

    public boolean updateEmployee(EmployeeDto employeeDto) {
        return employeeDao.update(employeeDto.uuid(), create(employeeDto));
    }

    public boolean removeEmployee(UUID uuid) {
        return employeeDao.delete(uuid);
    }

    public boolean addEmployee(EmployeeDto employeeDto) {
        String dtoUUID = employeeDao.instance(create(employeeDto)).getUuid().toString();

        return dtoUUID.equals(employeeDto.uuid().toString());
    }

}
