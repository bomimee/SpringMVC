<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>res</h2>
	<c:choose>
		<c:when test="${res>=2 }">
			<h3>${txVO.customerId } sir/madam</h3>
			<h3>${txVO.amount } paid</h3>
			<h3>${txVO.countnum } ticket purchased</h3>
		</c:when>
		<c:otherwise>
			<h2>cancel transaction</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>