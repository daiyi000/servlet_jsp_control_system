package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;
import service.UserService;

@WebServlet("/addUser")
public class AddUserController extends HttpServlet {
    private UserService userService;
    
    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        
        // 创建用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        
        // 调用业务层添加用户
        boolean success = userService.addUser(user);
        
        if (success) {
            // 添加成功，重定向到员工管理页面
            response.sendRedirect("manageUsers.jsp");
        } else {
            // 添加失败，设置错误信息后转发回员工管理页面
            request.setAttribute("errorMsg", "添加用户失败，请检查数据或该用户可能已存在！");
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
        }
    }
}
