package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.ProjectService;

@WebServlet("/deleteProject")
public class DeleteProjectController extends HttpServlet {
    private ProjectService projectService;
    
    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectIdStr = request.getParameter("projectId");
        if (projectIdStr != null) {
            int projectId = Integer.parseInt(projectIdStr);
            projectService.deleteProject(projectId);
        }
        // ɾ����ɺ��ض������Ŀ����ҳ��
        response.sendRedirect("manageProjects.jsp");
    }
}
