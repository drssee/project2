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
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/write.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/write.css">
    <link rel="stylesheet" type="text/css" href="../css/reset.css">
    <title>Write</title>
</head>
<body>
<!--title isFinished-->
<div id="write_wrap">
    <div>
        <p class="error vibration">${param.error}</p>
        <div class="write_container">
            <form action="/board/write" method="post">
                <input type="text" name="title" placeholder="제목을 입력">
                <textarea name="content" id="content" cols="30" rows="10" placeholder="내용을 입력"></textarea>
                <button type="submit">전송</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

