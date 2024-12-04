<%--
  Created by IntelliJ IDEA.
  User: Half_nothing
  Date: 2024/12/4
  Time: 下午7:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录页面</title>
    <link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="form" method="post" action="<c:url value="/login"/>">
    <p class="title">请登录</p>
    <div class="card">
        <label class="username">
            <span>账号</span>
            <input id="username" name="username" value="请输入用户名">
            <span id="username-span" class="input-tips"></span>
        </label>
        <label class="password">
            <span>密码</span>
            <input id="password" name="password" value="" type="password">
            <span id="password-span" class="input-tips"></span>
        </label>
        <label class="remember-me">
            <input type="checkbox" name="rememberMe">
            <span>七天内免密登录</span>
        </label>
        <span class="input-tips text-center"> ${empty pageScope.msg ? "" : pageScope.msg}</span>
        <button id="login" type="submit">登录</button>
    </div>
</form>
<script src="js/login.js" type="module"></script>
</body>
</html>
