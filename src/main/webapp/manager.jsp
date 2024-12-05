<%--
  Created by IntelliJ IDEA.
  User: Half_nothing
  Date: 2024/12/5
  Time: 下午3:49
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员工资管理系统-员工管理</title>
    <link href="css/common.css" rel="stylesheet" type="text/css">
    <link href="css/layout.css" rel="stylesheet" type="text/css">
    <link href="css/manager.css" rel="stylesheet" type="text/css">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="js/manager.js"></script>
    <script>
        <c:if test="${!sessionScope.login}">
        window.location = "login.jsp"
        </c:if>
        <c:if test="${!sessionScope.admin}">
        window.location = "home.jsp"
        </c:if>
    </script>
</head>
<body>
<div id="overlay">
    <div class="popup">
        <p class="popup_title">编辑信息</p>
        <div class="popup_content">
            <label>
                <span>用户名：</span>
                <input id="username">
            </label>
            <label>
                <span>姓名：</span>
                <input id="name">
            </label>
            <label>
                <span>职位：</span>
                <input id="post">
            </label>
            <label>
                <span>岗位级别：</span>
                <input id="level">
            </label>
            <label>
                <span>基础薪资：</span>
                <span id="base-payment">0</span>
            </label>
            <label>
                <span>最终薪资：</span>
                <input id="payment">
            </label>
        </div>
        <div class="popup_btn">
            <button class="cancelBtn" onclick="hidePopup()">取消</button>
            <button class="confirmBtn" onclick="submitAndHidePopup()">确认</button>
        </div>
    </div>
</div>
<div class="container">
    <div class="header">
        <a href="home.jsp"><span class="link" id="title">人员工资管理系统</span></a>
        <a href=""><span class="link active">员工管理</span></a>
        <a href="payment.jsp"><span class="link">基础薪资管理</span></a>
        <a href="post.jsp"><span class="link">岗位管理</span></a>
    </div>
    <div class="body">
        <table>
            <thead>
            <tr>
                <th>姓名</th>
                <th>职位</th>
                <th>岗位级别</th>
                <th>基础薪资</th>
                <th>最终薪资</th>
                <th>
                    <span>操作</span>
                    <button class="addButton" onclick="addUser()">添加</button>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.userList}" var="user">
                <tr>
                    <td>${user.nickname}</td>
                    <td>${user.post.postName}</td>
                    <td>${user.post.postLevel.postLevel}</td>
                    <td>${user.post.postLevel.postPayment}</td>
                    <td>${user.realPay}</td>
                    <td>
                        <button class="editButton" onclick="editUser(${user.id})">编辑</button>
                        <button class="deleteButton" onclick="deleteUser(${user.id})">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="footer"></div>
</div>
</body>
</html>
