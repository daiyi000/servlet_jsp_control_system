package service;

import java.util.List;

import dao.ProjectDao;
import model.Project;

public class ProjectService {
    private ProjectDao projectDao;

    public ProjectService() {
        projectDao = new ProjectDao();
    }

    public boolean addProject(Project project) {
        return projectDao.addProject(project);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    public Project getProjectById(int projectId) {
        return projectDao.getProjectById(projectId);
    }

    public boolean updateProject(Project project) {
        return projectDao.updateProject(project);
    }
    public boolean deleteProject(int projectId) {
        return projectDao.deleteProject(projectId);
    }

}
