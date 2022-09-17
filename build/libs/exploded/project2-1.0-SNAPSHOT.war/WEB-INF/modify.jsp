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
    <script src="../js/modify.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/modify.css">
    <link rel="stylesheet" type="text/css" href="../css/modify.css">
    <title>Modify</title>
</head>
<body>
<!--title isFinished-->
<div id="modify_wrap">
    <div>
        <p class="error vibration">${param.error}</p>
        <div class="modify_container">
            <form action="/board/modify" method="post">
                <input type="hidden" name="bno" value="${boardDTO.bno}">
                <input type="hidden" name="writer" value="${boardDTO.writer}">
                <input type="text" name="title" value="${boardDTO.title}">
<%--                <textarea name="content" id="content" cols="30" rows="10" value="${boardDTO.content}"></textarea>--%>
                <textarea name="content" id="content" cols="30" rows="10">${boardDTO.content}</textarea>
                <button type="submit">전송</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>


