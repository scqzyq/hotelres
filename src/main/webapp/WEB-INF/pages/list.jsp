<%--
  Created by IntelliJ IDEA.
  User: scqzy
  Date: 20-3-5
  Time: 上午12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${list}" var="user">
    ${user.username}
</c:forEach>

</body>
</html>
