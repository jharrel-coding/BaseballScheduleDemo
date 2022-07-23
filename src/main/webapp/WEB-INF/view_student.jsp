<%--
  Created by IntelliJ IDEA.
  User: jharr
  Date: 7/23/2022
  Time: 9:59 AM
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
    <title>${student.name}</title>
</head>
<body>

<h1>${student.name}'s classes</h1>
<p><a href="/">Dashboard</a></p>

<form action="/students/${student.id}" method="post">

    <table>
        <thead>
        <tr>
            <td>
                Classes
                <select name="courseId" id="courseId" class="input">
                    <c:forEach var="course" items="${allCourses}">
                        <c:if test = "${allCourses!=null}">
                            <option value="${course.id}">${course.name}</option>
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
<h4>Your Schedule</h4>
<table>
    <thead>
    <tr>
        <th>Class Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="course" items="${student.courses}">
        <tr>
            <td><a href="/classes/${course.id}"><c:out value="${course.name}"></c:out></a></td>
            <td><a href="/classes/remove/${student.id}/${course.id}">Drop</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>