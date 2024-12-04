<%--
  Created by IntelliJ IDEA.
  User: Half_nothing
  Date: 2024/12/4
  Time: 上午5:54
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员工资管理系统</title>
    <link href="css/common.css" rel="stylesheet" type="text/css">
    <link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="header">
        <span id="title">人员工资管理系统</span>
        <c:if test="${sessionScope.login}">
            <span class="button link">员工管理</span>
            <span class="button link">工资查询</span>
        </c:if>
    </div>
    <div class="body">
        <c:if test="${!sessionScope.login}">
            <iframe id="iframe" src="login.jsp" width="800" height="600">对不起,您的浏览器不支持iframe</iframe>
        </c:if>
        <c:if test="${sessionScope.login}">
            <iframe id="iframe" src="home.jsp" width="800" height="600">对不起,您的浏览器不支持iframe</iframe>
        </c:if>
    </div>
    <div class="footer">
    </div>
    <script src="js/index.js" type="module"></script>
</div>
</body>
</html>
