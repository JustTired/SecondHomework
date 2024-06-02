package JDBC.dao;

import JDBC.dto.EmployeeFilter;
import JDBC.entities.CompanyEntity;
import JDBC.entities.EmployeeEntity;
import JDBC.exceptions.DaoException;
import JDBC.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.joining;

public class EmployeeDao implements DaoInterface<UUID, EmployeeEntity> {
    private static final EmployeeDao INSTANCE = new EmployeeDao();

    private EmployeeDao() {
    }

    public static EmployeeDao getInstance() {
        return INSTANCE;
    }

    @Override
    public EmployeeEntity instance(EmployeeEntity employeeEntity) {
        try (var connection = ConnectionPool.get()) {
            return create(employeeEntity, connection);
        } catch (
                SQLException e) {
            throw new DaoException("Insert Employee failed", e);
        }
    }

    private EmployeeEntity create(EmployeeEntity employeeEntity, Connection connection) throws DaoException {
        try (var statement = connection.prepareStatement(
                SqlRequestConstants.INSERT_EMPLOYEE_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setObject(1, employeeEntity.getUuid());
            statement.setString(2, employeeEntity.getFirstName());
            statement.setString(3, employeeEntity.getLastName());
            statement.setString(4, employeeEntity.getRole());
            statement.setString(5, employeeEntity.getEmail());
            statement.setString(6, employeeEntity.getCompany().getName());
            statement.executeUpdate();

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                employeeEntity.setUuid(generatedKeys.getObject("id", UUID.class));
                employeeEntity.setFirstName(generatedKeys.getString("first_name"));
                employeeEntity.setLastName(generatedKeys.getString("last_name"));
                employeeEntity.setRole(generatedKeys.getString("role"));
                employeeEntity.setEmail(generatedKeys.getString("email"));
                employeeEntity.setCompany(new CompanyEntity(generatedKeys.getString("companyname")));
            }
        } catch (SQLException e) {
            throw new DaoException("Insert Employee failed", e);
        }
        return employeeEntity;
    }


    @Override
    public List<EmployeeEntity> readAll() {
        List<EmployeeEntity> employees = new ArrayList<>();
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(SqlRequestConstants.READ_DB)) {

            var results = statement.executeQuery();
            while (results.next()) {
                employees.add(
                        EmployeeDao.getInstance().create(results)
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Read all Employees failed ", e);
        }
        return employees;
    }

    // TODO Снести к хренам
    public List<EmployeeEntity> readAll(EmployeeFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();

        if (filter.role() != null) {
            whereSql.add("role = ?");
            parameters.add(filter.role());
        }
        if (filter.email() != null) {
            whereSql.add("email = ?");
            parameters.add(filter.email());
        }


        parameters.add(filter.limit());
        parameters.add(filter.offset());
        String where;
        if (!whereSql.isEmpty()) {
            where = whereSql.stream()
                    .collect(joining(" AND ", "WHERE ", " \nLIMIT ? \nOFFSET ? "));
        } else {
            where = " LIMIT ? OFFSET ?";
        }
        String sql = SqlRequestConstants.READ_DB + where;
        try (Connection connection = ConnectionPool.get();
             var statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            var results = statement.executeQuery();
            List<EmployeeEntity> employees = new ArrayList<>();
            while (results.next()) {
                employees.add(create(results));
            }
            return employees;
        } catch (SQLException e) {
            throw new DaoException("Read Employees with filter failed ", e);
        }
    }

    @Override
    public EmployeeEntity readFirst() {
        EmployeeEntity employee = null;
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(SqlRequestConstants.READ_DB)) {

            statement.executeQuery();
            var results = statement.getResultSet();
            if (results.next()) {
                employee = EmployeeDao.getInstance().create(results);
            }
        } catch (SQLException e) {
            throw new DaoException("Read first Employee failed ", e);
        }
        return employee;
    }

    @Override
    public boolean update(UUID identify, EmployeeEntity employeeEntity) {
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(SqlRequestConstants.UPDATE_EMPLOYEE_QUERY)) {

            statement.setString(1, employeeEntity.getFirstName());
            statement.setString(2, employeeEntity.getLastName());
            statement.setString(3, employeeEntity.getRole());
            statement.setString(4, employeeEntity.getEmail());
            statement.setString(5, String.valueOf(identify));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update Employee failed ", e);
        }

    }

    @Override
    public boolean delete(UUID identify) {
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(SqlRequestConstants.DELETE_EMPLOYEE)) {

            statement.setObject(1, identify);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Delete Employee failed ", e);
        }
    }

    private EmployeeEntity create(ResultSet results) throws SQLException {

        return new EmployeeEntity(
                results.getObject("id", UUID.class),
                results.getString("first_name"),
                results.getString("last_name"),
                results.getString("role"),
                results.getString("email"),
                new CompanyEntity(results.getString("companyname"))
        );
    }
}
