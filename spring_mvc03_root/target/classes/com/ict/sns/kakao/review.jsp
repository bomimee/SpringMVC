<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script>
const path = "${path}";
const handleSubmit = function(f){
	f.action = "uploadReview.do";
	f.submit();
}
</script>
<script defer src="${path}/resources/public/js/review.js"></script>
<link href="${path}/resources/public/css/review.css" rel="stylesheet" />

</head>
<body>

	 <form class="container" method="post"  enctype="multipart/form-data">
      <div class="review-container">
        <div class="review-header">
          <h1 class="title">리뷰작성</h1>
          <button class="cancel" onclick="handleCancel()"></button>
        </div>
        <div class="review-product">
          <div class="img-small" style="background-img:"></div>
          <span class="product-name">${pro_name}</span>
        </div>
        <div class="selectStar">
          <div class="q-spans">
            <span class="review-q-black">상품은 어떠셨나요?</span>
            <span class="review-q-gray">별점을 매겨주세요</span>
          </div>
          <div class="stars">
            <img src="${path}/resources/imgs/star.png" class="star star--1" data-point="1" />
            <img src="${path}/resources/imgs/star.png" class="star star--2" data-point="2" />
            <img src="${path}/resources/imgs/star.png" class="star star--3" data-point="3" />
            <img src="${path}/resources/imgs/star.png" class="star star--4" data-point="4" />
            <img src="${path}/resources/imgs/star.png" class="star star--5" data-point="5" />
          </div>
        </div>
        <div class="file-container">
          <div class="review-file">
            <label class="file-button file-button--1" for="file"> </label>
            <input type="file" class="file file--1" style="display: none" />
            <button class="remove">&times;</button>
          </div>
          <div class="review-file">
            <label class="file-button file-button--2" for="file"> </label>
            <input type="file" class="file file--2" style="display: none" />
            <button class="remove">&times;</button>
          </div>

          <div class="review-file">
            <label class="file-button file-button--3" for="file"> </label>
            <input type="file" class="file file--3" style="display: none" />
            <button class="remove">&times;</button>
          </div>
        </div>
        <textarea
          class="text"
          placeholder="최소 15자 이상 작성해주세요"
        ></textarea>

        <p class="lightgray-text">
          이미지 리뷰는 상품이 노출된 사진이 1장 이상 포함되어야 합니다.(최대 3장)
        </p>

        <button class="button-black" onclick="handleSubmit(this.form)">등록하기</button>
      </div>
    </form>

</body>
</html>