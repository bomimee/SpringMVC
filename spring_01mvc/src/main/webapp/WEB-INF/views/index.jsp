<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function f_up(f){
	f.submit();
}
function oracle_list(){
	location.href="oracle_list.do";
}
function maria_list(){
	location.href="maria_list.do";
}
function guestbook_list(){
	location.href="guestbook_list.do";
}
</script>
</head>
<body>
	<h1>first spring mvc</h1>
	<!-- 확장자가 .do 이므로 web.xml에서 지정한 servlet-context.xml (디스패처 서블릿) -->

	<h2>
		<a href="start1.do">start1</a>
	</h2>
	<h2>
		<a href="start2.do">start2</a>
	</h2>
	<h2>
		<a href="start3.do">start3</a>
	</h2>
	<h2>
		<a href="start4.do">start4</a>
	</h2>
	<h2>
		<a href="start5.do">start5</a>
	</h2>
	<h2>
		<a href="start6.do">start6</a>
	</h2>
	<hr>
	<h2>
		<a href="hello.do">hello</a>
	</h2>
	<hr>
	<form action="calc.do" method="post">
		<p>
			number1 : <input type="number" size="15" name="s1"
				required>
		</p>
		<p>
			number2 : <input type="number" size="15" name="s2" required>
		</p>
		<p>
			op : <input type="radio" name="op" value="+" checked>+ <input
				type="radio" name="op" value="-" >- <input
				type="radio" name="op" value="*" >* <input
				type="radio" name="op" value="/" >/
		</p>
		
		<p>
			hobby : <input type="checkbox" name="hobby" value="축구" checked>축구 <input
				type="checkbox" name="hobby" value="야구" >야구 <input
				type="checkbox" name="hobby" value="농구" >농구 <input
				type="checkbox" name="hobby" value="배구" > 배구
		</p>
		<input type="hidden" name="cPage" value="2"> 
		<input type="submit" value="send">
	</form>
	
	<h2> picture</h2>
	<img alt="img" src="/resources/imgs/car1.jpg" style="width: 100px"><br>
	<img alt="img" src="/resources/imgs/car2.jpg" style="width: 100px"><br>
	<img alt="img" src='<c:url value="/resources/imgs/car1.jpg"/>' style="width: 100px"><br>
	<img alt="img" src='<c:url value="/resources/imgs/car2.jpg"/>' style="width: 100px"><br>
	
	
	<!--파일 업로드:: jsp  에서는 cos library 사용 
	스프링에서 라이브러리 관리는 maven(builder) 이한다. (pom.xml) 
	https://mvnrepository.com/ 에서 cos 찾아서 pom.xml에 붙여넣기-->
	<h2>file upload1</h2>
	<form action="f_up.do" method="post" enctype="multipart/form-data">
	<p> uploader: <input type="text" name="name"></p>
	<p> file: <input type="file" name="f_name"></p>
	<p> <input type="submit" value="upload"></p>
	</form>
	<h2>file upload2</h2>
	<form action="f_up.do" method="post" enctype="multipart/form-data">
	<p> uploader: <input type="text" name="name"></p>
	<p> file: <input type="file" name="f_name"></p>
	<p><input type="button" value="upload" onclick="f_up(this.form)"></p>
	</form>
	<h2>file upload3</h2>
	<form action="f_up2.do" method="post" enctype="multipart/form-data">
	<p> uploader: <input type="text" name="name"></p>
	<p> file: <input type="file" name="f_name"></p>
	<p> <input type="submit" value="upload"></p>
	</form>
	<hr>
	<button onclick="oracle_list()"> oracle members list </button>
	<button onclick="maria_list()"> mariadb members list </button>
	<button onclick="guestbook_list()"> oracle guestbook list </button>
</body>

</html>