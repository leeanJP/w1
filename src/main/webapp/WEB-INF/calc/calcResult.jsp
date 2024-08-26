<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>NUM1 ${param.num1}</h1>
    <h1>NUM2 ${param.num2}</h1>

    <h1>SUM ${Integer.parseInt(param.num1) + Integer.parseInt(param.num2)}</h1>

    <%--
        1. JSP에서 쿼리스트링이나 파라미터를 처리하지 않는다. - JSP 대신 서블릿을 통해 처리
        2. JSP는 입력화면을 구성하거나 처리 결과를 보여주는 용도
        3. 브라우저는 직접 JSP 경로를 호출하지 않고 서블릿 경로를 통해서 JSP를 보는 방식으로 사용
    --%>

    <%--
        MVC 구조로 설계하는 계산기

        - 브라우저의 호출은 반드시 컨트롤러 역할을 하는 서블릿을 호출하도록 구성
        - JSP는 브라우저에서 직접 호출하지 않고 컨트롤러를 통해서만 JSP에 접근하도록 구성


    --%>

</body>
</html>
