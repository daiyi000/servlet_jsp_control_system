<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>登录 -  版本号控制系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- 引入外部 CSS 文件，路径根据您项目实际情况修改 -->
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
      <!-- 登录表单容器 -->
      <div class="login-container">
        <h1>登录</h1>
        <form action="login" method="post">
          <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" name="username" id="username" required/>
          </div>
          <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" id="password" required/>
          </div>
          <div class="btn-container">
            <input type="submit" value="登录" class="btn"/>
          </div>
        </form>

        <!-- 错误提示信息（若有） -->
        <% 
          String errorMsg = (String) request.getAttribute("errorMsg");
          if (errorMsg != null) {
        %>
          <p class="error-message"><%= errorMsg %></p>
        <% } %>

        <!-- 实时显示当前时间 -->
        <div id="timeDisplay"></div>
        <script>
          function updateTime() {
            var now = new Date();
            var formattedTime = now.getFullYear() + '-' +
              String(now.getMonth() + 1).padStart(2, '0') + '-' +
              String(now.getDate()).padStart(2, '0') + ' ' +
              String(now.getHours()).padStart(2, '0') + ':' +
              String(now.getMinutes()).padStart(2, '0') + ':' +
              String(now.getSeconds()).padStart(2, '0');
            document.getElementById("timeDisplay").textContent = formattedTime;
          }
          updateTime();
          setInterval(updateTime, 1000);
        </script>
      </div>

      

      <!-- 其他链接（注册、返回主页等） -->
      <div class="links">
        <p>还没有账号？<a href="register.jsp">立即注册</a></p>
        <p><a href="index.jsp">返回主页</a></p>
      </div>
    </main>

    <!-- 页脚 -->
    <footer class="footer">
      <p>© 2025  版本号控制系统</p>
    </footer>
  </div>
</body>
</html>
