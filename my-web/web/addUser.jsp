<%--
  Created by IntelliJ IDEA.
  User: margarita
  Date: 17.12.18
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить нового пользователя</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/phonebook" method="post">
    <input required type="text" name="name" placeholder="Name">
    <input required type="text" name="phone" placeholder="Phone">
    <input type="submit" value="Add">
</form>

</body>
</html>

