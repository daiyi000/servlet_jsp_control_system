<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>首页 -  版本号控制系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
  <div class="wrapper">
    <!-- 页眉 -->
    <header class="header">
      <div class="title"> 版本号控制系统</div>
      <!-- 直接保留普通导航链接即可 -->
      <nav class="nav-links">
        <a href="index.jsp">首页</a>
        <a href="login.jsp">登录</a>
        <a href="register.jsp">注册</a>
        <a href="about.jsp">关于我们</a>
      </nav>
    </header>

    <!-- 主体内容 -->
    <main class="main">
      <div class="container">
        <h1>欢迎来到  版本号控制系统</h1>
        <p>本系统用于管理 版本号控制。</p>
        <div id="timeDisplay" style="font-size:16px; color:#333;"></div>
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
        <p>请登录或注册以开始使用系统。</p>
        
        <div class="info">
          <h2>系统功能</h2>
          <ul>
            <li>管理员：登录、注册，并管理项目经理信息。</li>
            <li>项目经理：登录、注册，进行项目的添加、编辑、删除与查询。</li>
            <li>版本控制：实时管理 版本号更新记录。</li>
          </ul>
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
