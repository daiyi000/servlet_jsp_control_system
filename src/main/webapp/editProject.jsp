<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 从 request 中获取待编辑的项目对象
    model.Project editProject = (model.Project) request.getAttribute("project");
    if (editProject == null) {
        out.println("未找到需要编辑的项目信息！");
        return;
    }
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑项目信息</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="header">
        <div class="title">项目管理系统 - 编辑项目</div>
        <div class="nav-links">
            <a href="manageProjects.jsp">返回项目管理</a>
        </div>
    </div>
    <div class="container">
        <h1>编辑项目信息</h1>
        <form action="project?action=update" method="post">
            <!-- 隐藏字段传递项目ID -->
            <input type="hidden" name="projectId" value="<%= editProject.getProjectId() %>" />
            <div class="form-group">
                <label for="projectName">项目名称：</label>
                <input type="text" name="projectName" id="projectName" value="<%= editProject.getProjectName() %>" required/>
            </div>
            <div class="form-group">
                <label for="versionNumber">版本号：</label>
                <input type="text" name="versionNumber" id="versionNumber" value="<%= editProject.getVersionNumber() %>" required/>
            </div>
            <input type="submit" value="更新项目信息"/>
        </form>
    </div>
    <div class="footer">
        <p>© 2025  版本号控制系统</p>
    </div>
</body>
</html>
