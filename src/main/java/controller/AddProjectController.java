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
        // 获取表单参数
        String projectName = request.getParameter("projectName");
        String versionNumber = request.getParameter("versionNumber");
        
        // 创建项目对象
        Project project = new Project();
        project.setProjectName(projectName);
        project.setVersionNumber(versionNumber);
        
        // 调用业务层添加项目
        boolean success = projectService.addProject(project);
        
        if (success) {
            // 添加成功，重定向到项目管理页面
            response.sendRedirect("manageProjects.jsp");
        } else {
            // 添加失败，设置错误信息后转发回项目管理页面
            request.setAttribute("errorMsg", "添加项目失败，请检查数据！");
            request.getRequestDispatcher("manageProjects.jsp").forward(request, response);
        }
    }
}
