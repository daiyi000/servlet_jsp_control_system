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
        String role = "user"; // Ĭ�Ͻ�ɫ�� "user"������Ը��������޸ġ�
        String email = request.getParameter("email");  // ��������ע��ʱ�ռ�������

        if (password.equals(confirmPassword)) {
            // ʵ���� UserDao ������û����Ƿ��ѱ�ע�ᣬ�������û�ע��
            UserDao userDao = new UserDao();
            boolean success = userDao.registerUser(username, password, role, email);

            if (success) {
                // ע��ɹ�����ת����¼ҳ��
                response.sendRedirect("login.jsp");
            } else {
                // ���ע��ʧ�ܣ������û����Ѵ��ڣ������ش�����Ϣ
                request.setAttribute("errorMsg", "�û����Ѵ���");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            // ������벻ƥ��
            request.setAttribute("errorMsg", "�����������벻һ��");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}


