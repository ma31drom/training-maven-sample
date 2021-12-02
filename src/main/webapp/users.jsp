<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добро пожаловать, JSP!</title>
</head>
<body>
	<h1>Добро пожаловать!</h1>

	<c:choose>
		<c:when test="${users ne null}">
			<table>
				<tr>
					<th>Id</th>
					<th>First name</th>
					<th>Last name</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td><a
							href="/training-maven-sample/deleteUser?userId=${user.id}">Delete
								user </a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<c:out value="No users found" />
		</c:otherwise>
	</c:choose>

	<c:if test="${deletedId ne null}">User with id ${deletedId} was deleted successfully</c:if>

	<c:if test="${addedId ne null}">User with id ${addedId} was added successfully</c:if>

	<!-- POST /training-maven-sample/addUser?fname=blabla&lname=blaba -->

	<form action="/training-maven-sample/addUser">
		<label for="fname">First name:</label><br> <input type="text"
			id="fname" name="fname" value="John"><br> <label
			for="lname">Last name:</label> <br> <input type="text"
			id="lname" name="lname" value="Doe"><br> <br> <input
			type="submit" value="Submit">
	</form>
</body>
</html>