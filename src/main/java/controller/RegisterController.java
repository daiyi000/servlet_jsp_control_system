package controller;

import java.io.IOException;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = "user"; // 默认角色是 "user"，你可以根据需求修改。
        String email = request.getParameter("email");  // 假设你在注册时收集了邮箱

        if (password.equals(confirmPassword)) {
            // 实例化 UserDao 来检查用户名是否已被注册，并进行用户注册
            UserDao userDao = new UserDao();
            boolean success = userDao.registerUser(username, password, role, email);

            if (success) {
                // 注册成功后，跳转到登录页面
                response.sendRedirect("login.jsp");
            } else {
                // 如果注册失败（例如用户名已存在），返回错误信息
                request.setAttribute("errorMsg", "用户名已存在");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            // 如果密码不匹配
            request.setAttribute("errorMsg", "两次密码输入不一致");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}


