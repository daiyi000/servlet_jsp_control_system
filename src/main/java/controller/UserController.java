package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import model.User;
import service.UserService;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "edit":        // 编辑员工信息
                editUser(request, response);
                break;
            case "profile":     // 编辑个人信息
                editProfile(request, response);
                break;
            default:
                // 如果没有匹配的操作，跳回用户管理页面或者做其他处理
                response.sendRedirect("manageUsers.jsp");
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
            case "update":          // 更新员工信息
                updateUser(request, response);
                break;
            case "updateProfile":   // 更新个人信息
                updateProfile(request, response);
                break;
            default:
                response.sendRedirect("manageUsers.jsp");
                break;
        }
    }

    /**
     * 1. 编辑员工信息（管理员点击“编辑”时跳转）
     */
    private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        if (userIdStr == null) {
            response.sendRedirect("manageUsers.jsp");
            return;
        }
        int userId = Integer.parseInt(userIdStr);

        User user = userService.getUserById(userId);
        if (user == null) {
            response.sendRedirect("manageUsers.jsp");
            return;
        }

        request.setAttribute("user", user);
        // 转发到 editUser.jsp
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }

    /**
     * 2. 编辑个人信息（用户点击“修改个人信息”时跳转）
     */
    private void editProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 通常会从 session 中拿到当前登录用户ID
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute("user", user);
        // 转发到 editProfile.jsp
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

    /**
     * 3. 更新员工信息（管理员提交编辑表单后执行）
     */
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);

        userService.updateUser(user);

        // 更新完成后，重定向回员工管理页面
        response.sendRedirect("manageUsers.jsp");
    }

    /**
     * 4. 更新个人信息（用户提交修改个人信息后执行）
     */
    private void updateProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // role 通常不允许修改，如果允许，请自行添加

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        userService.updateUser(user);

        // 更新完成后，跳回个人信息或主页
        response.sendRedirect("editProfile.jsp"); 
        // 或者跳转到其他页面，比如 "manager.jsp"
    }
}
