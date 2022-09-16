<%--
  Created by IntelliJ IDEA.
  User: 502
  Date: 2022-09-05
  Time: 오후 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/login.js"></script>
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/reset.css">
    <title>Login</title>
</head>
<body>
<div id="login_wrap">
    <p class="error vibration">${param.error}</p>
    <div class="login_container">
        <form action="/todo/login" method="post">
            <input type="text" name="id" placeholder="아이디입력" value="skagus">
            <input type="password" name="pwd" placeholder="비밀번호입력" value="rlaskagus1">
            <button type="submit">Click</button>
        </form>
    </div>
</div>
</body>
</html>
