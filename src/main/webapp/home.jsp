<%--
  Created by IntelliJ IDEA.
  User: Half_nothing
  Date: 2024/12/4
  Time: 下午10:44
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <title>人员工资管理系统-主页</title>
    <link href="css/common.css" rel="stylesheet" type="text/css">
    <link href="css/layout.css" rel="stylesheet" type="text/css">
    <link href="css/home.css" rel="stylesheet" type="text/css">
    <script src="js/home.js" type="module"></script>
    <c:if test="${!sessionScope.login}">
        <script>
            window.location = "login.jsp"
        </script>
    </c:if>
</head>
<body>
<div class="container">
    <div class="header">
        <a href=""><span class="link active" id="title">人员工资管理系统</span></a>
        <c:if test="${sessionScope.admin}">
            <a href="manager.jsp"><span class="link">员工管理</span></a>
            <a href="payment.jsp"><span class="link">工资查询</span></a>
        </c:if>
    </div>
    <div class="body">
        <div class="user-info">
            <div class="user-info-card">
                <img src="image/user.png" alt="">
                <span>${sessionScope.username}</span>
                <span>${sessionScope.admin ? "管理员" : "普通员工"}</span>
            </div>
            <div class="logout-button">
                <a href="<c:url value="/logout"/>"><span>退出登录</span></a>
            </div>
        </div>
        <div class="data">
            <div class="time">
                <span>当前系统时间：</span>
                <span id="time"></span>
            </div>
            <div class="online-visitor">
                <span>当前在线人数：${applicationScope.onlineVisitor}</span>
            </div>
            <div class="history-visitor">
                <span>历史访问人数：${applicationScope.historyVisitor}</span>
            </div>
        </div>
    </div>
    <div class="footer"></div>
</div>
</body>
</html>
