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
        // ��ȡ������
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        
        // �����û�����
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        
        // ����ҵ�������û�
        boolean success = userService.addUser(user);
        
        if (success) {
            // ��ӳɹ����ض���Ա������ҳ��
            response.sendRedirect("manageUsers.jsp");
        } else {
            // ���ʧ�ܣ����ô�����Ϣ��ת����Ա������ҳ��
            request.setAttribute("errorMsg", "����û�ʧ�ܣ��������ݻ���û������Ѵ��ڣ�");
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
        }
    }
}
