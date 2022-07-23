<%--
  Created by IntelliJ IDEA.
  User: jharr
  Date: 7/23/2022
  Time: 9:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>All Classes</title>
</head>
<body>

<h1>All Classes</h1>
<p><a href="/">Dashboard</a></p>

<table>
    <thead>
    <tr>
        <th>Class</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td><a href="/classes/${course.id}"><c:out value="${course.name}"></c:out></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>