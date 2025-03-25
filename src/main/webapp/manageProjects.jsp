<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProjectDao" %>
<%@ page import="dao.UserDao" %>
<%@ page import="model.Project" %>
<%@ page import="model.User" %>
<%
    // 获取 session 中的用户角色
    String role = (String) session.getAttribute("role");
    if (role == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    ProjectDao projectDao = new ProjectDao();
    UserDao userDao = new UserDao();
    
    // 获取查询关键字
    String searchTerm = request.getParameter("searchTerm");
    List<Project> projects;
    if (searchTerm != null && !searchTerm.trim().isEmpty()) {
        // 需要在 ProjectDao 中实现 searchProjects(String keyword)
        projects = projectDao.searchProjects(searchTerm);
    } else {
        projects = projectDao.getAllProjects();
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>项目信息管理 -  版本号控制系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
  <div class="wrapper">
    <!-- 页眉 -->
    <header class="header">
      <div class="title">项目管理系统 - 项目管理</div>
      <nav class="nav-links">
        <a href="index.jsp">首页</a>
        <a href="manager.jsp">返回管理首页</a>
      </nav>
    </header>

    <!-- 主体内容 -->
    <main class="main">
      <div class="container">
        <h1>项目信息管理</h1>

        <% if ("user".equals(role)) { %>
          <!-- 添加项目区域（仅项目经理可见） -->
          <div class="box">
            <h2>添加项目</h2>
            <form action="addProject" method="post">
              <div class="form-group">
                <label for="projectName">项目名称：</label>
                <input type="text" name="projectName" id="projectName" required/>
              </div>
              <div class="form-group">
                <label for="versionNumber">版本号：</label>
                <input type="text" name="versionNumber" id="versionNumber" required/>
              </div>
              <div class="btn-container">
                <input type="submit" value="添加项目" class="btn"/>
              </div>
            </form>
          </div>
        <% } %>

        <!-- 查询表单区域 -->
        <div class="box">
          <h2>查询项目</h2>
          <form action="manageProjects.jsp" method="get">
            <div class="form-group">
              <input type="text" name="searchTerm" placeholder="输入项目名称或版本号查询"
                     value="<%= searchTerm != null ? searchTerm : "" %>"/>
            </div>
            <div class="btn-container">
              <input type="submit" value="查询" class="btn"/>
            </div>
          </form>
        </div>

        <!-- 项目列表区域 -->
        <div class="box">
          <h2>项目列表</h2>
          <table>
            <thead>
              <tr>
                <th>项目名称</th>
                <th>版本号</th>
                <th>操作人</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <% for (Project p : projects) {
                   int operatorId = p.getOperatorId();
                   User operatorUser = userDao.getUserById(operatorId);
                   String operatorName = operatorUser != null ? operatorUser.getUsername() : "未知";
              %>
                <tr>
                  <td><%= p.getProjectName() %></td>
                  <td><%= p.getVersionNumber() %></td>
                  <td><%= operatorName %></td>
                  <td>
                    <% if ("user".equals(role)) { %>
                      <a href="project?action=edit&projectId=<%= p.getProjectId() %>" class="btn">编辑</a>
                      <a href="deleteProject?projectId=<%= p.getProjectId() %>" class="btn-delete"
                         onclick="return confirm('确定要删除此项目吗？');">删除</a>
                    <% } else if ("admin".equals(role)) { %>
                      <span>查看</span>
                    <% } %>
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
