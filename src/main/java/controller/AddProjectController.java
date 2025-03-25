package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Project;
import service.ProjectService;

@WebServlet("/addProject")
public class AddProjectController extends HttpServlet {
    private ProjectService projectService;
    
    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ��ȡ������
        String projectName = request.getParameter("projectName");
        String versionNumber = request.getParameter("versionNumber");
        
        // ������Ŀ����
        Project project = new Project();
        project.setProjectName(projectName);
        project.setVersionNumber(versionNumber);
        
        // ����ҵ��������Ŀ
        boolean success = projectService.addProject(project);
        
        if (success) {
            // ��ӳɹ����ض�����Ŀ����ҳ��
            response.sendRedirect("manageProjects.jsp");
        } else {
            // ���ʧ�ܣ����ô�����Ϣ��ת������Ŀ����ҳ��
            request.setAttribute("errorMsg", "�����Ŀʧ�ܣ��������ݣ�");
            request.getRequestDispatcher("manageProjects.jsp").forward(request, response);
        }
    }
}
