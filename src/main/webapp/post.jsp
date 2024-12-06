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
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="js/post.js"></script>
    <c:if test="${!sessionScope.login}">
        <script>
            window.location = "login.jsp"
        </script>
    </c:if>
    <c:if test="${!sessionScope.user.admin}">
        <script>
            window.location = "home.jsp"
        </script>
    </c:if>
</head>
<body>
<div id="overlay">
    <div class="popup">
        <p class="popup_title">编辑信息</p>
        <div class="popup_content">
            <label>
                <span>岗位名：</span>
                <input id="name">
            </label>
            <label>
                <span>岗位级别：</span>
                <input id="level">
            </label>
            <label>
                <span>基础工资：</span>
                <span id="payment">0</span>
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
        <a href="home.jsp"><span class="link active" id="title">人员工资管理系统</span></a>
        <a href="<c:url value='/users/list'/>"><span class="link">员工管理</span></a>
        <a href="<c:url value='/payments/list'/>"><span class="link">基础薪资管理</span></a>
        <a href="<c:url value='/posts/list'/>"><span class="link">岗位管理</span></a>
    </div>
    <div class="body">
        <table>
            <thead>
            <tr>
                <th>岗位名</th>
                <th>岗位级别</th>
                <th>基础工资</th>
                <th>
                    <span>操作</span>
                    <button class="addButton" onclick="addPost()">添加</button>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.postList}" var="post">
                <tr>
                    <td>${post.postName}</td>
                    <td>${post.postLevel.name}</td>
                    <td>${post.postLevel.payment}</td>
                    <td>
                        <button class="editButton" onclick="editPost(${post.id})">编辑</button>
                        <button class="deleteButton" onclick="deletePost(${post.id})">删除</button>
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
