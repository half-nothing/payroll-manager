<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Half_nothing
  Date: 2024/12/5
  Time: 下午10:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员工资管理系统-岗位管理</title>
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
        <a href="home.jsp"><span class="link active" id="title">人员工资管理系统</span></a>
        <c:if test="${sessionScope.admin}">
            <a href="<c:url value='/users/list'/>"><span class="link">员工管理</span></a>
            <a href="payment.jsp"><span class="link">基础薪资管理</span></a>
            <a href=""><span class="link">岗位管理</span></a>
        </c:if>
    </div>
    <div class="body"></div>
    <div class="footer"></div>
</div>
</body>
</html>
