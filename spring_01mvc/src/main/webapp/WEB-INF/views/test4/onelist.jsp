<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 600px; margin:auto; text-align: center;}
</style>
<script>
function update_go(f){
	//check 
	f.action = "gb_update.do";
	f.submit();
}
function delete_go(f){
	//check 
	f.action = "gb_delete.do";
	f.submit();
}
</script>
</head>
<body>
	<div>
		<h2>방명록</h2>
		<hr />
		<p>[<a href="guestbook_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${g_vo.name}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td>${g_vo.subject}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${g_vo.email}</td>
				</tr>
		
				<tr style="text-align: left;">
					<td colspan="2">
						<pre style="padding-left: 15px;">${g_vo.content}</pre>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
						<input type="hidden" name="idx" value="${g_vo.idx}">
						<input type="hidden" name="pwd" value="${g_vo.pwd}">
							<input type="button" value="수정" onclick="update_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
							<input type="button" value="삭제" onclick="delete_go(this.form)"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>