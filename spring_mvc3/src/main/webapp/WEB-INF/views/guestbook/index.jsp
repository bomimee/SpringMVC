<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function send1_go(){
		location.href="gb_list.do";
	}
	function send_go(){
		location.href="gb2_list.do";
	}
</script>
</head>
<body>
<button onclick="send1_go()">Guestbook1</button>
<button onclick="send_go()">Guestbook2</button>
</body>
</html>