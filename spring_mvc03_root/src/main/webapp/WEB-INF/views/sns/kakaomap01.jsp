<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="map" style="width:100%;height:350px;"></div>

<%-- 
카카오 디벨로퍼 로그인 후 내 어플리케이션 - 자바스크립트 키 : 0e8d5c9dd32c99625e83c24860f61570
 --%>
 
 <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0e8d5c9dd32c99625e83c24860f61570"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 
</script>
</body>
</html>