<%--
  Created by IntelliJ IDEA.
  User: K
  Date: 2021/4/19
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <h3>findAll</h3>
    ${accounts}<br><br>
    <c:forEach items="${accounts}" var="account" varStatus="vs" >
        ${vs.count}<br>
        ${account.name}-${account.money}<br>
    </c:forEach>
</body>
</html>
