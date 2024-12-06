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
        <c:if test="${sessionScope.user.admin}">
            <a href="<c:url value='/users/list'/>"><span class="link">员工管理</span></a>
            <a href="<c:url value='/payments/list'/>"><span class="link">基础薪资管理</span></a>
            <a href="<c:url value='/posts/list'/>"><span class="link">岗位管理</span></a>
        </c:if>
    </div>
    <div class="body">
        <div class="user-info">
            <div class="user-info-card">
                <img src="image/user.png" alt="">
                <span>${sessionScope.user.username}</span>
                <span>${sessionScope.user.admin ? "管理员" : "普通员工"}</span>
            </div>
            <div class="logout-button">
                <a href="<c:url value="/auth/logout"/>"><span>退出登录</span></a>
            </div>
        </div>
        <div class="data">
            <div class="time">
                <span>当前系统时间：</span>
                <span id="time"></span>
            </div>
            <div>
                <span>岗位：${sessionScope.user.post.postName}</span>
            </div>
            <div>
                <span>岗位级别：${sessionScope.user.post.postLevel.name}</span>
            </div>
            <div>
                <span>基础工资：${sessionScope.user.post.postLevel.payment}</span>
            </div>
            <div>
                <span>最终工资：${sessionScope.user.realPay}</span>
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
