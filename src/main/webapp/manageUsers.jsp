<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UserDao" %>
<%@ page import="model.User" %>
<%
    // 使用内置 session 对象获取当前用户身份
    String role = (String) session.getAttribute("role");
    if (role == null || !"admin".equals(role)) {
        // 若未登录或角色不是管理员，则跳转到登录页面
        response.sendRedirect("login.jsp");
        return;
    }

    UserDao userDao = new UserDao();
    // 获取查询关键字
    String searchTerm = request.getParameter("searchTerm");
    List<User> users;
    if (searchTerm != null && !searchTerm.trim().isEmpty()) {
        // 在 UserDao 中实现 searchUsers(String keyword) 方法
        users = userDao.searchUsers(searchTerm);
    } else {
        users = userDao.getAllUsers();
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>员工管理 -  版本号控制系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
  <div class="wrapper">
    <!-- 页眉 -->
    <header class="header">
      <div class="title">项目管理系统 - 员工管理</div>
      <nav class="nav-links">
        <a href="index.jsp">首页</a>
        <a href="manager.jsp">返回管理首页</a>
      </nav>
    </header>

    <!-- 主体内容 -->
    <main class="main">
      <div class="container">
        <h1>员工管理</h1>

        <!-- 查询表单区域 -->
        <div class="box">
          <h2>查询员工</h2>
          <form action="manageUsers.jsp" method="get">
            <div class="form-group">
              <input type="text" name="searchTerm" placeholder="输入用户名或邮箱查询"
                     value="<%= searchTerm != null ? searchTerm : "" %>"/>
            </div>
            <div class="btn-container">
              <input type="submit" value="查询" class="btn"/>
            </div>
          </form>
        </div>

        <!-- 添加员工表单区域 -->
        <div class="box">
          <h2>添加员工</h2>
          <form action="addUser" method="post">
            <div class="form-group">
              <label for="username">用户名：</label>
              <input type="text" name="username" id="username" required/>
            </div>
            <div class="form-group">
              <label for="password">密码：</label>
              <input type="password" name="password" id="password" required/>
            </div>
            <div class="form-group">
              <label for="role">角色：</label>
              <select name="role" id="role">
                <option value="user">项目经理</option>
                <option value="admin">管理员</option>
              </select>
            </div>
            <div class="form-group">
              <label for="email">邮箱：</label>
              <input type="email" name="email" id="email" required/>
            </div>
            <div class="btn-container">
              <input type="submit" value="添加员工" class="btn"/>
            </div>
          </form>
        </div>

        <!-- 员工列表区域 -->
        <div class="box">
          <h2>员工列表</h2>
          <table>
            <thead>
              <tr>
                <th>用户名</th>
                <th>角色</th>
                <th>邮箱</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <% for (User u : users) { %>
                <tr>
                  <td><%= u.getUsername() %></td>
                  <td><%= u.getRole() %></td>
                  <td><%= u.getEmail() %></td>
                  <td>
                    <!-- 编辑与删除操作 -->
                    <a href="user?action=edit&userId=<%= u.getUserId() %>" 
                       class="btn">编辑</a>
                    <a href="deleteUser?userId=<%= u.getUserId() %>" 
                       onclick="return confirm('确定要删除此用户吗？');" class="btn-delete">删除</a>
                  </td>
                </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </main>

    <!-- 页脚 -->
    <footer class="footer">
      <p>© 2025  版本号控制系统</p>
    </footer>
  </div>
</body>
</html>
