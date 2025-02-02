<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 명 록</title>
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse;
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	padding: 3px
}

div {
	width: 600px;
	margin: auto;
	text-align: center;
}
</style>
<script>
	function update_go(f) {
		//check 
		f.action = "gb2_update.do";
		f.submit();
	}
	function delete_go(f) {
		//check 
		f.action = "gb2_delete.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록</h2>
		<hr />
		<p>
			[<a href="gb2_list.do">목록으로
				이동</a>]
		</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${vo.name}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제 목</td>
					<td>${vo.subject}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${vo.email}</td>
				</tr>
				
				<tr align="center">
					<td bgcolor="#99ccff">file</td>
					<c:choose>
						<c:when test="${empty vo.filename}">
							<td>첨부파일 없음</td>
						</c:when>
						<c:otherwise>
							<td><a href="guestbook2_down.do?filename=${vo.filename}">
								<img src="resources/upload/${vo.filename}" style="width:80px">${vo.filename}</a>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				
				<tr style="text-align: left;">
					<td colspan="2"><pre style="padding-left: 15px;">${vo.content}</pre>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2"><input type="hidden" name="idx"
							value="${vo.idx}"> <input type="hidden" name="pwd"
							value="${vo.pwd}"> <input type="button" value="수정"
							onclick="update_go(this.form)" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="삭제" onclick="delete_go(this.form)" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>