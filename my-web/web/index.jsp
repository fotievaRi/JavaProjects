<%--
  Created by IntelliJ IDEA.
  User: margarita
  Date: 15.12.18
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <title>Блокнот</title>
</head>
<body>
<style>
  .frm_block {
    display: block;
    margin: 0 auto;
  }
</style>
<table class="frm_block">
  <tr>
    <td><form action = "${pageContext.request.contextPath}/addUser.jsp">
      <input type="submit" value="Add new person or only phone number">
    </form></td>
    <td><form action = "${pageContext.request.contextPath}/phonebook" method="get">
      <input type="submit" value="Show phonebook">
    </form></td>
  </tr>
</table>
</body>
</html>


