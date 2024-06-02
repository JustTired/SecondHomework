package JDBC.services;

import JDBC.dao.ProjectDao;
import JDBC.dto.ProjectDto;
import JDBC.entities.ProjectEntity;

import java.util.List;

public class ProjectService {
    private static final ProjectService INSTANCE = new ProjectService();
    private static final ProjectDao PROJECT_DAO_INSTANCE = ProjectDao.getInstance();

    private ProjectService() {
    }

    public static ProjectService getInstance() {
        return INSTANCE;
    }

    public ProjectDto getFirstProject() {
        var project = PROJECT_DAO_INSTANCE.readFirst();

        return new ProjectDto(project.getName(),
                project.getDate());
    }

    public List<ProjectDto> getAllProjects() {
        return PROJECT_DAO_INSTANCE.readAll().stream()
                .map(dao -> new ProjectDto(dao.getName(), dao.getDate()))
                .toList();
    }

    private ProjectEntity create(ProjectDto projectDto) {
        return new ProjectEntity(projectDto.name(), projectDto.startDate());
    }

    public boolean updateProject(ProjectDto projectDto) {
        return PROJECT_DAO_INSTANCE.update(
                projectDto.name(),
                create(projectDto));
    }

    public boolean deleteProject(String identify) {
        return PROJECT_DAO_INSTANCE.delete(identify);
    }

    public boolean addProject(ProjectDto projectDto) {
        return PROJECT_DAO_INSTANCE.add(create(projectDto)).getName()
                .equals(projectDto.name());
    }

}
