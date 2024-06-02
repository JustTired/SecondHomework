package JDBC.dao;

import JDBC.entities.ProjectEntity;
import JDBC.exceptions.DaoException;
import JDBC.util.ConnectionPool;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static JDBC.dao.SqlRequestConstants.*;

public class ProjectDao implements DaoInterface<String, ProjectEntity> {
    private ProjectDao() {
    }

    private static final ProjectDao INSTANCE = new ProjectDao();

    public static ProjectDao getInstance() {
        return INSTANCE;
    }

    @Override
    public ProjectEntity instance(ProjectEntity projectEntity) {
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(INSERT_PROJECT_QUERY)) {
            statement.setString(1, projectEntity.getName());
            statement.setDate(2, projectEntity.getDate());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                projectEntity.setName("name");
                projectEntity.setDate(Date.valueOf("startdate"));
            }
        } catch (SQLException e) {
            throw new DaoException("Create project failed", e);
        }
        return projectEntity;
    }

    @Override
    public List<ProjectEntity> readAll() {
        List<ProjectEntity> projectEntities = new ArrayList<>();
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(READ_DB)) {
            var results = statement.executeQuery();
            while (results.next()) {
                projectEntities.add(ProjectDao.getInstance().create(results));
            }
        } catch (SQLException e) {
            throw new DaoException("Read all projects failed", e);
        }
        return projectEntities;
    }

    @Override
    public ProjectEntity readFirst() {
        ProjectEntity projectEntity = null;
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(READ_DB)) {
            var results = statement.executeQuery();
            if (results.next()) {
                projectEntity = ProjectDao.getInstance().create(results);
            }
        } catch (SQLException e) {
            throw new DaoException("Read first project failed", e);
        }
        return projectEntity;
    }

    @Override
    public boolean update(String identify, ProjectEntity projectEntity) {
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(UPDATE_PROJECT_QUERY)) {
            statement.setDate(1, projectEntity.getDate());
            statement.setString(2, identify);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update project failed", e);
        }
    }

    @Override
    public boolean delete(String identify) {
        try (var connection = ConnectionPool.get();
             var statement = connection.prepareStatement(DELETE_PROJECT)) {
            statement.setString(1, identify);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Delete project failed", e);
        }
    }

    private ProjectEntity create(ResultSet resultSet) throws SQLException {
        return new ProjectEntity(
                resultSet.getString("name"),
                resultSet.getDate("startdate")
        );
    }
}
