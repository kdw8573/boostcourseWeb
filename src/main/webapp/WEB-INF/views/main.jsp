<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="css/style.css" rel="stylesheet" />
</head>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
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
		<hr>
		<div class="event">
			<div class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="prev_e" style="display: none;">
							<div class="prev_inn">
								<a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i>
								</a>
							</div>
						</div>
						<div class="nxt_e" style="display: none;">
							<div class="nxt_inn">
								<a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i>
								</a>
							</div>
						</div>
						<div>
							<div class="container_visual">
								<!-- 슬라이딩기능: 이미지 (type = 'th')를 순차적으로 노출 -->
								<ul class="visual_img" style="transition: all 0.5s ease;">
									<li style="float: left; position: relative;"><img style="height: 177px;" src="/reservation/img/12_th_32.png"></li>
									<li style="float: left; position: relative;"><img style="height: 177px;" src="/reservation/img/21_th_53.png"></li>
									<li style="float: left; position: relative;"><img style="height: 177px;" src="/reservation/img/22_th_55.png"></li>
									<li style="float: left; position: relative;"><img style="height: 177px;" src="/reservation/img/23_th_57.png"></li>
									<li style="float: left; position: relative;"><img style="height: 177px;" src="/reservation/img/24_th_59.png"></li>
								</ul>
							</div>
							<span class="nxt_fix" style="display: none;"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0"><a class="anchor active"> <span>전체리스트</span>
					</a></li>
					<li class="item" data-category="1"><a class="anchor"> <span>전시</span>
					</a></li>
					<li class="item" data-category="2"><a class="anchor"> <span>뮤지컬</span>
					</a></li>
					<li class="item" data-category="3"><a class="anchor"> <span>콘서트</span>
					</a></li>
					<li class="item" data-category="4"><a class="anchor"> <span>클래식</span>
					</a></li>
					<li class="item" data-category="5"><a class="anchor"> <span>연극</span>
					</a></li>
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가 <span class="pink">${count}개</span> 있습니다
				</p>
				<div class="wrap_event_box">
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top" onclick="window.scrollTo(0,0);"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
	<script type="text/javascript" src="/reservation/js/sliderJs.js"></script>
	<script>
	document.addEventListener("DOMContentLoaded", ready);
	
	function ready() {
		slide.init();
		
		let firstProductList = [];

		<c:forEach var="product" items="${list}" >
			firstProductList.push({id: "${product.id}"
				,content: "${product.content}"
				,description : "${product.description}"
				,placeName : "${product.placeName}"
				,saveFileName : "${product.saveFileName}"});
		</c:forEach>
		
		makeTemplate(firstProductList);
		
		const tabmenu = document.querySelector(".tab_lst_min");
		tabmenu.addEventListener("click",function(e){
			clickTabAjax(e.target.closest(".item"));
		});
		
	}
	
	//template 치환하는 함수
	function makeTemplate(list){
		const templateHTML = document.getElementById("itemList").innerHTML;
		let bindTemplate = Handlebars.compile(templateHTML);
		let resultHTML = list.reduce(function(prevHTML, nextHTML, index) {
			if(index == 2){ 
				prevHTML += "</ul><ul class='lst_event_box'>";	
			}	
			return prevHTML + bindTemplate(nextHTML);
		}, "<ul class='lst_event_box'>");		
		resultHTML += "</ul>";

		if(list.length == ${limit}) {
			resultHTML += "<div class='more'><button class='btn' onclick='clickMoreBtnAjax();'><span>더보기</span></button></div>"
		}
		document.querySelector(".wrap_event_box").innerHTML += resultHTML;
		
	}
	
	function changeTabCategory(selectedCategory, selectingCategory) {
		selectedCategory.classList.remove("active");
		selectingCategory.firstChild.classList.add("active");
		document.querySelector(".wrap_event_box").innerHTML = "";
	}
	
	function hideMoreBtn() {
		const moreBtn= document.getElementsByClassName('more');
		moreBtn[moreBtn.length - 1].style.visibility = "hidden";
	}
	
	function clickTabAjax(selectingCategory) {
		const xhr = new XMLHttpRequest();
		const data = {
		  start: 0,
		  category: selectingCategory.dataset.category
		};
		
		xhr.open('POST', '/reservation/main');
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(data));
		
		xhr.onload = function() {
			  if (xhr.status === 200 || xhr.status === 201) { 
				  changeTabCategory(document.getElementsByClassName("anchor active")[0], selectingCategory);
				  makeTemplate(JSON.parse(xhr.response));
			  } else {
			    console.error("fail");
			  }
		};
	}
	
	//더보기 버튼 누를 시 작동되는 ajax
	function clickMoreBtnAjax() {
		const xhr = new XMLHttpRequest();
		const categoryNum = document.getElementsByClassName("anchor active")[0].closest(".item").dataset.category;
		const data = {
		  start: document.getElementsByName('play').length, //화면에 표시된 연극 개수
		  category: categoryNum
		};
		
		xhr.open('POST', '/reservation/main');
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(data));
		
		xhr.onload = function() {
		  if (xhr.status === 200 || xhr.status === 201) {  
			  hideMoreBtn();
			  makeTemplate(JSON.parse(xhr.response));
		  } else {
		    console.error("fail");
		  }
		};
	}
	</script>

	<script type="rv-template" id="promotionItem">
    <li class="item" style="background-image: url(http://211.249.62.123/productImages/${productId}/${productImageId});">
        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
            <div class="event_txt">
                <h4 class="event_txt_tit"></h4>
                <p class="event_txt_adr"></p>
                <p class="event_txt_dsc"></p>
            </div>
        </a>
    </li>
    </script>

	<script type="rv-template" id="itemList">
        <li class="item" name="play">
            <a href="/reservation/detail?id={{id}}" class="item_book">
                <div class="item_preview">
                    <img alt="{{description}}" id="img{{id}}" class="img_thumb" src="/reservation/{{saveFileName}}">
                    <span class="img_border"></span>
                </div>
                <div class="event_txt">
                    <h4 class="event_txt_tit"> <span>{{description}}</span> <small class="sm">{{placeName}}</small> </h4>
                    <p class="event_txt_dsc">{{content}}</p>
                </div>
            </a>
        </li>
    </script>
</body>
</html>