<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <form action="/todo/register" method="post">
        <div>
            <input type="text" name="title" placeholder="INSERT TITLE">
        </div>
        <div>
            <input type="date" name="dueDate">
        </div>
        <div>
            <button type="reset">RESET</button>
            <button type="submit">등록처리</button>
        </div>
    </form>

</body>
</html>
