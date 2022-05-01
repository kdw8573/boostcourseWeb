<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/reservation" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="/reservation" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<c:if test="${sessionScope.email != null}">
					<a href="/reservation/login" class="btn_my"> <span class="viewReservation" title="예약확인">${sessionScope.email}</span></a>
				</c:if>
				<c:if test="${sessionScope.email == null}">
					<a href="/reservation/login" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span>
					</a>
				</c:if>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
			<form method="post" action="uploadReview" enctype="multipart/form-data" onsubmit="return checkFormSubmit();">
				<div class="top_title review_header">
					<a href="/reservation/myreservation" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i>
					</a>
					<h2>
						<span class="title">${product.description}</span>
					</h2>
				</div>
				<!-- 리뷰 별점 -->
				<div class="write_act">
					<p class="title_star">별점과 이용경험을 남겨주세요.</p>
					<div class="review_rating rating_point">
						<div class="rating">
							<!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input엘리먼트에 checked 클래스 추가 -->
							<input type="checkbox" name="rating2" value="1" class="rating_rdo" title="1점"> <span class="span"></span> 
							<input type="checkbox" name="rating3" value="2" class="rating_rdo" title="2점"> <span class="span"></span> 
							<input type="checkbox" name="rating4" value="3" class="rating_rdo" title="3점"> <span class="span"></span> 
							<input type="checkbox" name="rating5" value="4" class="rating_rdo" title="4점"> <span class="span"></span> 
							<input type="checkbox" name="rating6" value="5" class="rating_rdo" title="5점"> <span class="span"></span>
							<!-- [D] 0점일 때 gray_star 추기 -->
							<span class="star_rank gray_star">0</span>
							<input type="hidden" name="score" value="0">
							<input type="hidden" name="id" value="${product.id}">
							<input type="hidden" name="productId" value="${product.productId}">
						</div>
					</div>
				</div>
				<!-- //리뷰 별점 -->

				<!-- 리뷰 입력 -->
				<div class="review_contents write">
					<!-- [D] review_write_info 클릭 시 자신을 숨기고 review_textarea 에 focus를 보낸다. -->
					<a class="review_write_info" onclick="writeReview(this);"> 
						<span class="middot"> 실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다. </span><br> 
						<span class="middot"> 소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다. </span> 
						<span class="left_space">(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)</span>
					</a>
					<textarea cols="30" rows="10" class="review_textarea" name="comment" maxlength="400" oninput="insertLetterLength(this);" onblur="checkReview(this);"></textarea>
				</div>
				<!-- //리뷰 입력 -->

				<!-- 리뷰 작성 푸터 -->
				<div class="review_write_footer_wrap">
					<div class="review_write_footer">
						<label class="btn_upload" for="reviewImageFileOpenInput"> 
							<i class="fn fn-image1" aria-hidden="true"></i> 
							<span class="text_add_photo">사진 추가</span>
						</label> 
						<input type="file" class="hidden_input" name="file" id="reviewImageFileOpenInput">
						<div class="guide_review">
							<span id="letterLength">0</span>/400 <span>(최소5자이상)</span>
						</div>
					</div>

					<!-- 리뷰 포토 -->
					<div class="review_photos review_photos_write">
						<div class="item_preview_thumbs">
							<ul class="lst_thumb">
								<li class="item" style="display: none;">
									<a class="anchor" onclick="deleteImage();"> <span class="spr_book ico_del">삭제</span></a> 
									<img src="" width="130" alt="" class="item_thumb"> <span class="img_border"></span>
								</li>
							</ul>
						</div>
					</div>
					<!-- //리뷰 포토 -->

				</div>
				<!-- //리뷰 작성 푸터 -->

				<!-- 리뷰 등록 -->
				<div class="box_bk_btn">
					<button type="submit" class="bk_btn">
						<span class="btn_txt">리뷰 등록</span>
					</button>
				</div>
				<!-- //리뷰 등록 -->
			</form>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top" onclick="window.scrollTo(0,0);"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	
	<script>
	document.addEventListener("DOMContentLoaded", ready);
	var starList = [];
	function StarRating(element){
		this.element = element;
		this.value = element.value;
	}
	StarRating.prototype.showRatingValue = function() {
		document.querySelector(".star_rank").innerText = this.value;
		document.querySelector(".star_rank").classList.remove("gray_star");
		document.querySelector("[name=score]").value = this.value;
		for(let i=0;i<5;i++){
			if(this.value < starList[i].value) {
				starList[i].element.classList.remove("checked");
				starList[i].element.checked = false;
			}
			else {
				starList[i].element.classList.add("checked");
			}
		}
	}
	function makeStarRatingObject(){	
		let selectorName = "";
		for(let i=2;i<=6;i++){
			selectorName = "input[name=rating" + i + "]";
			starList.push(new StarRating(document.querySelector(selectorName)));
		}
		
		const ratingElement = document.querySelector(".rating");
		ratingElement.addEventListener("click", function (evt) {
			for(let i=0;i<5;i++){
				if(starList[i].element == evt.target){
					starList[i].showRatingValue();
					break;
				}
			}
        });
	}
	function checkReview(e){
		if(e.value.length == 0){
			document.querySelector(".review_write_info").style.display = "block";
			alert("최소 5글자 이상 작성해주세요.");
			return false;
		}
		else if(e.value.length > 0 && e.value.length < 5){
			alert("최소 5글자 이상 작성해주세요.");
			return false;
		}
		return true;
	}
	function writeReview(e){
		e.style.display = "none";
		document.querySelector(".review_textarea").focus();
	}
	function insertLetterLength(e){
		document.querySelector("#letterLength").innerText = e.value.length;
	}
	function validImageType(image) {
		const result = ([ 'image/jpeg',
						  'image/png',
						  'image/jpg' ].indexOf(image.type) > -1);
		return result;
	}
	function checkFileType(){
		const elImage = document.querySelector("#reviewImageFileOpenInput");
		elImage.addEventListener("change", (evt) => {
			const image = evt.target.files[0];
			if(!validImageType(image)) { 
				alert("올바른 사진 확장자가 아닙니다.");
				return;
			}
			document.querySelector(".lst_thumb > .item").style.display="block";
			const imageElement = document.querySelector(".item_thumb");
			imageElement.src = window.URL.createObjectURL(image);
		})
	}
	function deleteImage() {
		const elImage = document.querySelector("#reviewImageFileOpenInput");
		elImage.value = "";
		document.querySelector(".lst_thumb > .item").style.display="none";
	}
	function checkFormSubmit(){
		let isTextareaLength = checkReview(document.querySelector(".review_textarea"));
		let starRatingValue = document.querySelector(".star_rank").innerText;
		if(isTextareaLength == true && starRatingValue != '0'){
			return true;
		}
		else if(starRatingValue == '0'){
			alert("별점을 매겨주세요.");
		}
		return false;
	}
	function ready(){
		makeStarRatingObject();
		checkFileType();
	}
	</script>
</body>
</html>