<%--
  Created by IntelliJ IDEA.
  User: 502
  Date: 2022-09-06
  Time: 오후 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="../css/register.css">
    <link rel="stylesheet" href="../css/reset.css">
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/register.js"></script>
    <title>Register</title>
</head>
<script>
    if(document.getElementById("input_check").checked){
        document.getElementById("input_check_hidden").disabled=true;
    }
</script>
<body>
<!--title isFinished-->
<div id="register_wrap">
    <div>
        <p class="error vibration">${param.error}</p>
        <div class="register_container">
            <form action="/todo/register" method="post">
                <input type="text" name="title" placeholder="제목을 입력">
                <input type="checkbox" name="isFinished" value="1" id="input_check" class="css-checkbox">
                <label for="input_check"></label><span class="isFinished">Yet</span>
                <input type="hidden" name="isFinished" value="0" id="input_check_hidden">
                <button type="submit">전송</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
