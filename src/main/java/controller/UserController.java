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
            case "edit":        // �༭Ա����Ϣ
                editUser(request, response);
                break;
            case "profile":     // �༭������Ϣ
                editProfile(request, response);
                break;
            default:
                // ���û��ƥ��Ĳ����������û�����ҳ���������������
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
            case "update":          // ����Ա����Ϣ
                updateUser(request, response);
                break;
            case "updateProfile":   // ���¸�����Ϣ
                updateProfile(request, response);
                break;
            default:
                response.sendRedirect("manageUsers.jsp");
                break;
        }
    }

    /**
     * 1. �༭Ա����Ϣ������Ա������༭��ʱ��ת��
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
        // ת���� editUser.jsp
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }

    /**
     * 2. �༭������Ϣ���û�������޸ĸ�����Ϣ��ʱ��ת��
     */
    private void editProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ͨ����� session ���õ���ǰ��¼�û�ID
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
        // ת���� editProfile.jsp
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

    /**
     * 3. ����Ա����Ϣ������Ա�ύ�༭����ִ�У�
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

        // ������ɺ��ض����Ա������ҳ��
        response.sendRedirect("manageUsers.jsp");
    }

    /**
     * 4. ���¸�����Ϣ���û��ύ�޸ĸ�����Ϣ��ִ�У�
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
        // role ͨ���������޸ģ�����������������

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        userService.updateUser(user);

        // ������ɺ����ظ�����Ϣ����ҳ
        response.sendRedirect("editProfile.jsp"); 
        // ������ת������ҳ�棬���� "manager.jsp"
    }
}
