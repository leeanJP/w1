<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>ToDo List</h1>

    <h2>${loginInfo}</h2>
    <h2>${loginInfo.mname}</h2>
    <ul>
        <c:forEach var="dto"  items="${list}">
            <li>
                <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
                <span>${dto.title}</span>
                <span>${dto.dueDate}</span>
                <span>${dto.finished ? "DONE" : "NOT YET"}</span>
            </li>
        </c:forEach>
    </ul>

    <form action="/logout" method="post">
        <button>LOGOUT</button>
    </form>

<%--    <c:if test="${list.size() % 2 == 0}">--%>
<%--        짝수--%>
<%--    </c:if>--%>
<%--    <c:if test="${list.size() % 2 != 0}">--%>
<%--        홀수--%>
<%--    </c:if>--%>

<%--    <c:choose>--%>
<%--        <c:when test="${list.size() % 2  == 0}">--%>
<%--            짝수--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            홀수--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>

</body>
</html>
