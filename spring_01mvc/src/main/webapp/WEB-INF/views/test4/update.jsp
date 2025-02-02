<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 명 록 수정</title>
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
<script type="text/javascript">
	function update_go(f) {
		const pwd = "${g_vo.pwd}";
		const pwd2 = f.pwd.value;
		if (pwd == pwd2) {
			f.action = "gb_update_ok.do"";
			f.submit();
		} else {
			alert("비밀번호틀림");
			f.pwd.value = "";
			f.pwd.focus();
			return;
		}
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 수정화면</h2>
		<hr />
		<p>
			[<a href="guestbook_list.do">목록으로
				이동</a>]
		</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td><input type="text" name="name" size="20"
						value="${g_vo.name }"></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제 목</td>
					<td><input type="text" name="subject" size="20"
						value="${g_vo.subject }"></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td><input type="text" name="email" size="20"
						value="${g_vo.email }"></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">file</td>
			
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="pwd" size="20"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><textarea rows="10" cols="60" name="content">${g_vo.content }</textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2"><input type="button" value="수정완료"
							onclick="update_go(this.form)" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" /></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>