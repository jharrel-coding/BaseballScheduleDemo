<%--
  Created by IntelliJ IDEA.
  User: jharr
  Date: 7/23/2022
  Time: 9:58 AM
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
    <title><c:out value="${dorm.name}"></c:out></title>
</head>
<body>

<h1><c:out value="${dorm.name}"></c:out> Students</h1>
<p><a href="/dorms">Dashboard</a></p>

<form action="/dorms/${dormId}" method="post">

    <table>
        <thead>
        <tr>
            <td>
                Students:
                <select name="studentId" id="studentId" class="input">
                    <c:forEach var="student" items="${allStudents}">
                        <c:if test = "${student.dorm==null}">
                            <option value="${student.id}">${student.name}</option>
                        </c:if>
                        <c:if test = "${student.dorm!=null}">
                            <option value="${student.id}">${student.name} (${student.dorm.name})</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td><input class="input" class="button" type="submit" value="Add"/></td>
        </tr>
        </thead>
    </table>
</form>
<hr>
<table>
    <thead>
    <tr>
        <th>Student</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${students}">
        <tr>
            <td><a href="/students/${student.id}"><c:out value="${student.name}"></c:out></a></td>
            <td><a href="/students/remove/${student.id}">Remove</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>