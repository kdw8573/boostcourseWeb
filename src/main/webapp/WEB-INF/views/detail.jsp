<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="css/style.css" rel="stylesheet">
<style>
.container_visual {
	height: 414px;
}
</style>
</head>

<body>
	<div id="container">
		<div class="ct main">
			<div>
				<div class="section_visual">
					<header>
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
					<div class="pagination">
						<div class="bg_pagination"></div>
						<div class="figure_pagination">
							<span id="imageNum" class="num">1</span> <span class="num off">/ <span id="totalImageNum">2</span>
							</span>
						</div>
					</div>
					<div class="group_visual">
						<div>
							<div class="container_visual" style="width: 414px;">
								<ul class="visual_img detail_swipe" style="width: 828px; transition: all 0.5s ease;">
									<li class="item" style="width: 414px; height: 414px;"><img alt="" name="mainImages" class="img_thumb" src=""> <span class="img_bg"></span>
										<div class="visual_txt">
											<div class="visual_txt_inn">
												<h2 class="visual_txt_tit">
													<span>${detailProduct.description }</span>
												</h2>
												<p class="visual_txt_dsc"></p>
											</div>
										</div></li>
									<li class="item" style="width: 414px; height: 414px;"><img alt="" name="mainImages" class="img_thumb" src=""> <span class="img_bg"></span>
										<div class="visual_txt">
											<div class="visual_txt_inn">
												<h2 class="visual_txt_tit">
													<span>${detailProduct.description }</span>
												</h2>
												<p class="visual_txt_dsc"></p>
											</div>
										</div></li>
								</ul>
							</div>
							<div name="pageBtn" class="prev">
								<div class="prev_inn" onclick="moveSlide();">
									<a class="btn_prev" title="이전"> <i class="spr_book2 ico_arr6_lt"></i>
									</a>
								</div>
							</div>
							<div name="pageBtn" class="nxt">
								<div class="nxt_inn" onclick="moveSlide();">
									<a class="btn_nxt" title="다음"> <i class="spr_book2 ico_arr6_rt"> </i>
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="group_btn_goto" style="display: none;">
						<a class="btn_goto_home" title="홈페이지" href="#" target="siteUrl"> <i class="fn fn-home1"></i>
						</a> <a class="btn_goto_tel" title="전화" href="#"> <i class="fn fn-call1"></i>
						</a> <a class="btn_goto_mail" title="이메일" href="#"> <i class="fn fn-mail1"></i>
						</a> <a href="#" class="btn_goto_path" title="길찾기"> <i class="fn fn-path-find1"></i>
						</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
					</div>
				</div>
				<div class="section_store_details">
					<!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
					<div class="store_details close3">
						<p class="dsc">${detailProduct.content}</p>
					</div>
					<!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
					<a id="bk_more_open" class="bk_more _open"> <span class="bk_more_txt">펼쳐보기</span> <i class="fn fn-down2"></i>
					</a> <a id="bk_more_close" class="bk_more _close" style="display: none;"> <span class="bk_more_txt">접기</span> <i class="fn fn-up2"></i>
					</a>
				</div>
				<div class="section_event">
					<div class="event_info_box">
						<div class="event_info_tit">
							<h4 class="in_tit">
								<i class="spr_book ico_evt"></i> <span>이벤트 정보</span>
							</h4>
						</div>
						<div class="event_info">
							<div class="in_dsc">
								[네이버예약 특별할인]<br>R석 50%, S석 60% 할인
							</div>
						</div>
					</div>
				</div>
				<div class="section_btn">
					<button type="button" class="bk_btn" onclick="location.href='/reservation/reserve?id=${detailProduct.id }'" >
						<i class="fn fn-nbooking-calender2"></i> <span>예매하기</span>
					</button>
				</div>
				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask"> 
									<c:if test="${scoreAvg != 'NaN'}">
										<em class="graph_value" style="width: ${scoreAvg * 20}%"></em>
									</c:if>
									<c:if test="${scoreAvg == 'NaN'}">
										<em class="graph_value" style="width: 0%"></em>
									</c:if>
								</span> <strong class="text_value"> <span>${scoreAvg }</span> <em class="total">5.0</em>
								</strong> <span class="join_count"> <em class="green">${commentLength }건</em> 등록
								</span>
							</div>
							<ul class="list_short_review">
							</ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
						</p>
					</div>
					<a class="btn_review_more" href="/reservation/review?id=${detailProduct.id }"> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i>
					</a>
				</div>
				<div class="section_info_tab">
					<!-- [D] tab 선택 시 anchor에 active 추가 -->
					<ul class="info_tab_lst">
						<li class="item active _detail"><a id="detailTab" class="anchor active"> <span>상세정보</span>
						</a></li>
						<li class="item _path"><a id="pathTab" class="anchor"> <span>오시는길</span>
						</a></li>
					</ul>
					<!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
					<div id="areaTab" class="detail_area_wrap">
						<div class="detail_area">
							<div class="detail_info">
								<h3 class="blind">상세정보</h3>
								<ul class="detail_info_group">
									<li class="detail_info_lst"><strong class="in_tit">[소개]</strong>
										<p class="in_dsc">${detailProduct.content }</p></li>
									<li class="detail_info_lst"><strong class="in_tit">[공지사항]</strong>
										<ul class="in_img_group">
											<li class="in_img_lst"><img alt="" class="img_thumb" src="/reservation/${detailProduct.saveFileName }"></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
					<div id="locationTab" class="detail_location hide">
						<div class="box_store_info no_topline">
							<a class="store_location" title="지도웹으로 연결"> <img class="store_map img_thumb" alt="map" src="/reservation/${detailProductMap}"> <span class="img_border"></span> <span class="btn_map"> <i class="spr_book2 ico_mapview"></i>
							</span>
							</a>
							<h3 class="store_name">${detailProduct.description}</h3>
							<div class="store_info">
								<div class="store_addr_wrap">
									<span class="fn fn-pin2"></span>
									<p class="store_addr store_addr_bold">${detailProduct.placeStreet}</p>
									<p class="store_addr">
										<span class="addr_old">지번</span> <span class="addr_old_detail">${detailProduct.placeLot} </span>
									</p>
									<p class="store_addr addr_detail">${detailProduct.placeName}</p>
								</div>
								<div class="lst_store_info_wrap">
									<ul class="lst_store_info">
										<li class="item"><span class="item_lt"> <i class="fn fn-call2"></i> <span class="sr_only">전화번호</span>
										</span> <span class="item_rt"> <a href="tel:02-548-0597" class="store_tel">${detailProduct.tel}</a></span></li>
									</ul>
								</div>
							</div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
							<div class="bottom_common_path column2">
								<a class="btn_path"> <i class="fn fn-path-find2"></i> <span>길찾기</span>
								</a> <a class="btn_navigation before"> <i class="fn fn-navigation2"></i> <span>내비게이션</span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a class="lnk_top" onclick="window.scrollTo(0,0);"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>

	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
	<script type="text/javascript" src="/reservation/js/makeTemplate.js"></script>
	<script>
	document.addEventListener("DOMContentLoaded", ready);
	
	var slideIndex = 0;
	function bkMoreToggle(){
		$("#bk_more_open").click(function () {
			$("#bk_more_open").css("display","none");
			$("#bk_more_close").css("display","");
			$(".section_store_details").children().toggleClass("close3");
		});
		$("#bk_more_close").click(function () {
			$("#bk_more_open").css("display","");
			$("#bk_more_close").css("display","none");
			$(".section_store_details").children().toggleClass("close3");
		});
	}
	
	function tabToggle(e){
		$("#detailTab").removeClass("active");
		$("#pathTab").removeClass("active");
		e.target.closest(".item").firstChild.classList.add("active");
		
		$("#areaTab").removeClass("hide");
		$("#locationTab").removeClass("hide");
		
		if(e.target.closest(".item").firstChild.id == "detailTab")
			$("#locationTab").addClass("hide");
		else
			$("#areaTab").addClass("hide");
		
	}
	
	function moveSlide() {
		slideIndex++;
		
		if (slideIndex == -1) {
	        slideIndex =  1;
	    } else if (slideIndex === 2) {
	        slideIndex = 0;
	    }
		document.querySelector("#imageNum").innerText = slideIndex + 1;
		document.querySelector(".detail_swipe").style.transform = "translateX("+ -(414 * slideIndex) + "px)";
	}
	
	function setMainImage(list){
		document.getElementsByName('mainImages')[0].src = list[0];
		document.querySelector("#totalImageNum").innerText = list.length; 
		
		if(list.length == 1){
			document.getElementsByName('pageBtn')[0].style.display = "none"; 
			document.getElementsByName('pageBtn')[1].style.display = "none";	
		}
		else if(list.length == 2){
			document.getElementsByName('mainImages')[1].src = list[1];
			
			setInterval(function () { moveSlide(); }, 3000);
		}
	}
	
	function loadMainImageAjax() {
		const xhr = new XMLHttpRequest();
		const data = {
		  id: ${detailProduct.id}
		};
		
		xhr.open('POST', '/reservation/loadMainImage');
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(data));
		
		xhr.onload = function() {
		  if (xhr.status === 200 || xhr.status === 201) {  
			 	setMainImage(JSON.parse(xhr.response));
		  } else {
		    console.error("fail");
		  }
		};
	}
	
	function ready() {
		bkMoreToggle();
		
		const tabmenu = document.querySelector(".info_tab_lst");
		tabmenu.addEventListener("click",function(e){
			tabToggle(e);
		});
		
		loadMainImageAjax();
		
		let commentList = [];
		<c:forEach var="comment" items="${comment3}" >
			commentList.push({reservationDate: "${comment.reservationDate}"
				,reservationName: "${comment.reservationName}"
				,score : "${comment.score}"
				,comment : "${comment.comment}"
				,saveFileName : "${comment.saveFileName}"});
		</c:forEach>
		makeTemplate(commentList, ".list_short_review")
	}
	</script>

	<script type="rv-template" id="listItemNoImage">
		<li class="list_item">
			<div>
				<div class="review_area no_img">
					<h4 class="resoc_name"></h4>
					<p class="review">{{comment}}</p>
				</div>
				<div class="info_area">
					<div class="review_info">
						<span class="grade">{{score}}</span> <span class="name">{{reservationName}}</span> <span class="date">{{reservationDate}}</span>
					</div>
				</div>
			</div>
		</li>
	</script>
	
	<script type="rv-template" id="listItemImage">
		<li class="list_item">
			<div>
				<div class="review_area">
					<div class="thumb_area">
						<a class="thumb" title="이미지 크게 보기"> <img width="90" height="90" class="img_vertical_top" src="/reservation/{{saveFileName}}" alt="리뷰이미지">
						</a> 
						<span class="img_count" style="display: none;">1</span>
					</div>
					<h4 class="resoc_name"></h4>
					<p class="review">{{comment}}</p>
				</div>
				<div class="info_area">
					<div class="review_info">
						<span class="grade">{{score}}</span> <span class="name">{{reservationName}}</span> <span class="date">{{reservationDate}}</span>
					</div>
				</div>
			</div>
		</li>
	</script>
</body>
</html>
