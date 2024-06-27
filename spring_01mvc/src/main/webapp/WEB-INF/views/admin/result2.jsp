<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>result2</h1>
	<h2>dog name</h2>
	<h3>
		<c:forEach var="k" items="${dogName}">
			<li>${k}</li>
		</c:forEach>
	</h3>
	<hr>

	<h2>harryporter</h2>
	<h3>
		<c:forEach var="k" items="${list }">
			<li>${k }</li>
		</c:forEach>
	</h3>
</body>
</html>