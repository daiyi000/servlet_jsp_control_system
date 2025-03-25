<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 从 request 中获取待编辑的用户对象
    model.User editUser = (model.User) request.getAttribute("user");
    if (editUser == null) {
        out.println("未找到需要编辑的用户信息！");
        return;
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>编辑用户信息 -  版本号控制系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
  <div class="wrapper">
    <!-- 页眉 -->
    <header class="header">
      <div class="title">项目管理系统 - 编辑用户</div>
      <nav class="nav-links">
        <a href="manageUsers.jsp">返回员工管理</a>
      </nav>
    </header>

    <!-- 主体内容 -->
    <main class="main">
      <div class="container">
        <h1>编辑用户信息</h1>
        <!-- 外层盒子容器，让表单更美观 -->
        <div class="box">
          <form action="user?action=update" method="post">
            <!-- 隐藏字段传递用户ID -->
            <input type="hidden" name="userId" value="<%= editUser.getUserId() %>" />

            <div class="form-group">
              <label for="username">用户名：</label>
              <input type="text" name="username" id="username" 
                     value="<%= editUser.getUsername() %>" required/>
            </div>

            <div class="form-group">
              <label for="password">密码：</label>
              <input type="password" name="password" id="password" 
                     value="<%= editUser.getPassword() %>" required/>
            </div>

            <div class="form-group">
              <label for="role">角色：</label>
              <select name="role" id="role">
                <option value="user"  <%= "user".equals(editUser.getRole())  ? "selected" : "" %>>项目经理</option>
                <option value="admin" <%= "admin".equals(editUser.getRole()) ? "selected" : "" %>>管理员</option>
              </select>
            </div>

            <div class="form-group">
              <label for="email">邮箱：</label>
              <input type="email" name="email" id="email" 
                     value="<%= editUser.getEmail() %>" required/>
            </div>

            <div class="btn-container">
              <input type="submit" value="更新用户信息" class="btn"/>
            </div>
          </form>
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
