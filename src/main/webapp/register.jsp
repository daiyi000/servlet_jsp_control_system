<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>注册 -  版本号控制系统</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
  <div class="wrapper">
    <!-- 页眉 -->
    <header class="header">
      <div class="title"> 版本号控制系统</div>
      <nav class="nav-links">
        <a href="index.jsp">首页</a>
        <a href="#">查看公告</a>
        <a href="#">联系我们</a>
      </nav>
    </header>

    <!-- 主体内容 -->
    <main class="main">
      <div class="container">
        <h1>用户注册</h1>
        <form action="register" method="post">
          <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" name="username" id="username" required/>
          </div>
          <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" id="password" required/>
          </div>
          <div class="form-group">
            <label for="confirmPassword">确认密码：</label>
            <input type="password" name="confirmPassword" id="confirmPassword" required/>
          </div>
          <div class="btn-container">
            <input type="submit" value="注册" class="btn"/>
          </div>
        </form>

        <% 
          String errorMsg = (String) request.getAttribute("errorMsg");
          if (errorMsg != null) {
        %>
          <p class="error-message"><%= errorMsg %></p>
        <% } %>

        <p>已有账号？<a href="login.jsp">立即登录</a></p>
        <p class="return-link"><a href="index.jsp">返回主页</a></p>
      </div>
    </main>

    <!-- 页脚 -->
    <footer class="footer">
      <p>© 2025  版本号控制系统</p>
    </footer>
  </div>
</body>
</html>
