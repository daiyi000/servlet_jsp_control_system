package controller;

import java.io.IOException;
import java.util.List;

import dao.ProjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Project;

@WebServlet("/searchProjects")
public class ProjectSearchController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        
        // 假设你有ProjectDao类来处理项目相关操作
        ProjectDao projectDao = new ProjectDao();
        List<Project> projects = projectDao.searchProjects(searchTerm);

        request.setAttribute("projects", projects);
        request.getRequestDispatcher("manager.jsp").forward(request, response);
    }
}
