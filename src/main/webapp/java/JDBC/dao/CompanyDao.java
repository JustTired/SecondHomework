package JDBC.dao;

import JDBC.entities.CompanyEntity;
import JDBC.exceptions.DaoException;
import JDBC.util.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static JDBC.util.SqlRequestConstants.*;

public class CompanyDao implements DaoInterface<String, CompanyEntity> {
    public static final String COLUMN_NAME = "company_name";

    private static final CompanyDao INSTANCE = new CompanyDao();

    private CompanyDao() {
    }

    public static CompanyDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(String companyName) {
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(DELETE_COMPANY)) {
            statement.setString(1, companyName);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error delete company ", e);
        }
    }

    @Override
    public CompanyEntity add(CompanyEntity company) {
        try (var connection = ConnectionPool.get();
             PreparedStatement statement = connection.prepareStatement(
                     INSERT_COMPANY_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, company.getName());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                company.setName(generatedKeys.getString(COLUMN_NAME));
            }
            return company;
        } catch (SQLException e) {
            throw new DaoException("Error inserting company ", e);
        }
    }

    @Override
    public boolean update(String companyName, CompanyEntity company) {
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(UPDATE_COMPANY_QUERY)) {
            statement.setString(1, company.getName());
            statement.setString(2, companyName);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error updating company ", e);
        }
    }

    @Override
    public List<CompanyEntity> readAll() {
        List<CompanyEntity> companyList = new ArrayList<>();
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(READ_DB)) {

            var results = statement.executeQuery();
            while (results.next()) {
                companyList.add(
                        new CompanyEntity(results.getString(COLUMN_NAME))
                );
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding all companies ", e);
        }
        return companyList;
    }

    @Override
    public CompanyEntity readFirst() {
        CompanyEntity company = null;
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(READ_DB)) {

            var results = statement.executeQuery();
            if (results.next()) {
                company = new CompanyEntity(results.getString(COLUMN_NAME));
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding company ", e);
        }
        return company;
    }


}
