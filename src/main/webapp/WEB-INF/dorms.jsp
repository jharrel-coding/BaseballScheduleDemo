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
    <title>Dorms</title>
</head>
<body>

<h2 class="center">Dorms</h2>
<p><a href="/dorms/new">Add a new dorm</a></p>
<p><a href="/students/add">Add a new student</a></p>
<p><a href="/classes/new">Add a new class</a></p>
<p><a href="/classes">View all classes</a></p>


<table>
    <thead>
    <tr>
        <th>Dorm</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dorm" items="${dorms}">
        <tr>
            <td><c:out value="${dorm.name}"></c:out></td>
            <td><a href="/dorms/${dorm.id}">See Students</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
