package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Project;
import utils.DBConnection;

public class ProjectDao {
    private Connection connection;

    public ProjectDao() {
        this.connection = DBConnection.getConnection();  
    }

    public boolean addProject(Project project) {
        String sql = "INSERT INTO project (project_name, version_number) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getVersionNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Project> getAllProjects() {
        List<Project> projectList = new ArrayList<>();
        String sql = "SELECT * FROM project";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                project.setVersionNumber(rs.getString("version_number"));
                project.setOperatorId(rs.getInt("operator_id"));
                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<Project> searchProjects(String searchTerm) {
        List<Project> projects = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM project WHERE project_name LIKE ? OR version_number LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Project project = new Project();
                        project.setProjectName(rs.getString("project_name"));
                        project.setVersionNumber(rs.getString("version_number"));
                        project.setOperatorId(rs.getInt("operator_id"));
                        projects.add(project);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }
    
    public Project getProjectById(int projectId) {
        String sql = "SELECT * FROM project WHERE project_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                project.setVersionNumber(rs.getString("version_number"));
                return project;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProject(Project project) {
        String sql = "UPDATE project SET project_name=?, version_number=?, operator_id=? WHERE project_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getVersionNumber());
            ps.setInt(3, project.getOperatorId());
            ps.setInt(4, project.getProjectId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProject(int projectId) {
        String sql = "DELETE FROM project WHERE project_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
