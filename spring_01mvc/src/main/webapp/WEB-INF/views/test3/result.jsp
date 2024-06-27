<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>result</h2>
<h3>
<li> uploader: ${name}</li>
<li> filename: ${f_name}</li>
<li> filetype: ${file_type}</li>
<li> size: ${size}KB</li>
<li> lastday: ${lastday}</li>
<li> download: <a href="down.do?f_name=${f_name }"></a><img src="resources/imgs/${f_name}"></a></li>
<li> download2: <a href="down2.do?f_name=${f_name }"></a><img src="resources/imgs/${f_name}"></a></li>


</h3>
</body>
</html>