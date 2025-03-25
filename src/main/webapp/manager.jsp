<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取 session 中的用户角色（直接使用 request.getSession()）
    String role = (String) request.getSession().getAttribute("role");
    if (role == null) {
        // 如果没有登录，跳转到登录页面
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>管理页面 - 项目管理系统</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
  <div class="wrapper">
    <!-- 页眉 -->
    <header class="header">
      <div class="title">项目管理系统</div>
      <nav class="nav-links">
        <a href="index.jsp">首页</a>
        <a href="#">查看公告</a>
        <a href="#">联系我们</a>
        <a href="logout" onclick="return confirm('确定要退出吗？');">退出</a>
      </nav>
    </header>

    <!-- 主体内容 -->
    <main class="main">
      <div class="container">
        <h1>欢迎, <%= request.getSession().getAttribute("username") %></h1>
        
        <% if ("admin".equals(role)) { %>
          <!-- 管理员：显示员工信息管理操作 -->
          <h2>员工信息管理</h2>
          <div class="btn-container">
            <a href="manageProjects.jsp" class="btn">查看任务</a>
            <a href="manageUsers.jsp" class="btn">管理员工</a>
          </div>
        <% } else if ("user".equals(role)) { %>
          <!-- 项目经理：显示项目信息管理操作 -->
          <h2>项目信息管理</h2>
          <div class="btn-container">
            <a href="user?action=profile" class="btn">编辑个人信息</a>
            <a href="manageProjects.jsp" class="btn">管理项目</a>
          </div>
        <% } else { %>
          <p>您的身份无法访问管理页面。</p>
        <% } %>
      </div>
    </main>

    <!-- 页脚 -->
    <footer class="footer">
      <p>© 2025  版本号控制系统</p>
    </footer>
  </div>
</body>
</html>
