<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: asvs
  Date: 2021/5/16
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <security:authorize access="hasRole('ADMIN')">
       <jsp:forward page="/pages/main.jsp"></jsp:forward>
   </security:authorize>
</body>
</html>
