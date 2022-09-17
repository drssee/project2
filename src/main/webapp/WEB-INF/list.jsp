<jsp:useBean id="pageResponseDTO" scope="session" type="com.example.demo2.dto.PageResponseDTO"/>
<%--
  Created by IntelliJ IDEA.
  User: 502
  Date: 2022-09-05
  Time: 오전 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/list.js"></script>
    <link rel="stylesheet" href="../css/list.css">
    <link rel="stylesheet" href="../css/reset.css">
    <title>List</title>
</head>
<body>
<div id="list_wrap">
    <div id="list_container">
        <div class="add_menu">
            <ul>
                <li>
                    <a href="/todo/register">추가</a>
                </li>
                <li>
                    <a href="/todo/update" onclick="return confirm('isFinished를 리셋 하시겠습니까?')">리셋</a>
                </li>
            </ul>
        </div>
        <ul class="todo_list">
            <c:forEach var="todo" items="${todoList}">
                <li>
                    <p>
                        <c:choose>
                            <c:when test="${todo.isFinished()}">
                                <a class="sel" href="/todo/modify?tno=${todo.tno}" onclick="return confirm('${todo.title}을 Not yet으로 바꾸시겠습니까?');"><img src="../images/love.png" alt="목록" width="32" height="32" title=""> ${todo}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/todo/modify?tno=${todo.tno}" onclick="return confirm('${todo.title} 완료할까요?');"><img src="../images/thinking.png" alt="목록" width="32" height="32"> ${todo}</a>
                            </c:otherwise>
                        </c:choose>
                        <a href="/todo/delete?tno=${todo.tno}" onclick="return confirm('${todo.title} 삭제할까요?');"><img src="../images/trash-can.png" alt="delete" width="25" height="25" class="trash"></a>
                    </p>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="board_wrap">
    <p class="add_menu2">
        <a href="/board/write">추가</a>
    </p>
    <table id="board">
        <tr>
            <th scope="col">title</th>
            <th scope="col">writer</th>
            <th scope="col">date</th>
        </tr>
        <c:forEach items="${boardDtoList}" var="boardDto" varStatus="status">
            <tr class="board_list1">
                <td>
                        ${boardDto.title}
                </td>
                <td>
                        ${boardDto.writer}
                </td>
                <td>
                        ${boardDto.getDatetimeS()}
                </td>
            </tr>
            <tr class="board_list2">
                <td colspan="3">
                    <textarea cols="20" rows="10" readonly class="content">${boardDto.content}</textarea>
                    <div>
                        <a href="/board/modify?bno=${boardDto.bno}&writer=${boardDto.writer}" class="btn_1">수정</a>
                        <a href="/board/delete?bno=${boardDto.bno}" class="btn_2" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="nav">
        <ul>
            <c:if test="${pageResponseDTO.showPrev}">
                <a href="/todo/list?page=${pageResponseDTO.prev}&size=${pageResponseDTO.size}">
                    <li>&lt;</li>
                </a>
            </c:if>
            <c:forEach begin="${pageResponseDTO.start}" end="${pageResponseDTO.end}" var="num">
                <a href="/todo/list?page=${num}&size=${pageResponseDTO.size}">
                    <li class="${num==pageResponseDTO.page?'sel2':''}">${num}</li>
                </a>
            </c:forEach>
            <c:if test="${pageResponseDTO.showNext}">
                <a href="/todo/list?page=${pageResponseDTO.next}&size=${pageResponseDTO.size}">
                    <li>&gt;</li>
                </a>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
