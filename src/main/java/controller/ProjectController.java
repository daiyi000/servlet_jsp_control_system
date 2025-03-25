package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import model.Project;
import service.ProjectService;

@WebServlet("/project")
public class ProjectController extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "edit":
                editProject(request, response);
                break;
            default:
                // 如果没有其他操作，就跳回项目管理页面
                response.sendRedirect("manageProjects.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "update":
                updateProject(request, response);
                break;
            default:
                response.sendRedirect("manageProjects.jsp");
                break;
        }
    }

    /**
     * 编辑项目
     */
    private void editProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectIdStr = request.getParameter("projectId");
        if (projectIdStr == null) {
            response.sendRedirect("manageProjects.jsp");
            return;
        }
        int projectId = Integer.parseInt(projectIdStr);

        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            response.sendRedirect("manageProjects.jsp");
            return;
        }

        request.setAttribute("project", project);
        // 转发到 editProject.jsp
        request.getRequestDispatcher("editProject.jsp").forward(request, response);
    }

    /**
     * 更新项目
     */
    private void updateProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        String projectName = request.getParameter("projectName");
        String versionNumber = request.getParameter("versionNumber");

     // 从session中获取当前操作的用户ID
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setVersionNumber(versionNumber);
        project.setOperatorId(userId);
        projectService.updateProject(project);

        // 更新完成后，重定向到项目管理页面
        response.sendRedirect("manageProjects.jsp");
    }
}
