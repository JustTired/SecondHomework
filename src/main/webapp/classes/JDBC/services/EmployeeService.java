package JDBC.services;

import JDBC.dao.EmployeeDao;
import JDBC.dto.EmployeeDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class EmployeeService {
    private static final EmployeeService INSTANCE = new EmployeeService();

    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        return INSTANCE;
    }

    private final EmployeeDao employeeDao = EmployeeDao.getInstance();

    public List<EmployeeDto> getAllEmployees() {
        return employeeDao.readAll().stream()
                .map(employeeEntity -> new EmployeeDto(
                        employeeEntity.getId(),
                        employeeEntity.getFirstName(),
                        employeeEntity.getRole(),
                        employeeEntity.getEmail()
                ))
                .collect(toList());
    }

    public String getFirstEmployee() {
        return employeeDao.readFirst().toString();
    }


}
