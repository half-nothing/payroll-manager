<%--
  Created by IntelliJ IDEA.
  User: Half_nothing
  Date: 2024/12/5
  Time: 下午3:50
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员工资管理系统-工资查询</title>
    <link href="css/common.css" rel="stylesheet" type="text/css">
    <link href="css/layout.css" rel="stylesheet" type="text/css">
    <c:if test="${!sessionScope.login}">
        <script>
            window.location = "login.jsp"
        </script>
    </c:if>
    <c:if test="${!sessionScope.admin}">
        <script>
            window.location = "home.jsp"
        </script>
    </c:if>
</head>
<body>
<div class="container">
    <div class="header">
        <a href="home.jsp"><span class="link" id="title">人员工资管理系统</span></a>
        <a href="manager.jsp"><span class="link">员工管理</span></a>
        <a href=""><span class="link active">工资查询</span></a>
    </div>
    <div class="body"></div>
    <div class="footer"></div>
</div>
</body>
</html>
