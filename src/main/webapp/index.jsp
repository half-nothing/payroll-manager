<%--
  Created by IntelliJ IDEA.
  User: Half_nothing
  Date: 2024/12/4
  Time: 上午5:54
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <title>人员工资管理系统</title>
    <script>
        <c:if test="${sessionScope.login}">
        window.location = "home.jsp"
        </c:if>
        <c:if test="${!sessionScope.login}">
        window.location = "login.jsp"
        </c:if>
    </script>
</head>
<body>
</body>
</html>
