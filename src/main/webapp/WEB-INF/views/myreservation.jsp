<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

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
					<a href="/reservation" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="/reservation" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="#" class="btn_my"> <span title="내예약" class="viewReservation"></span>
				</a>
			</header>
		</div>
		<hr>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary">
					<ul class="summary_board">
						<li class="item">
							<!--[D] 선택 후 .on 추가 link_summary_board --> 
							<a class="link_summary_board"> 
								<i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure">${fn:length(doingList)+ fn:length(doneList) + fn:length(cancelList)}</span>
							</a>
						</li>
						<li class="item">
							<a class="link_summary_board"> 	
								<i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure">${fn:length(doingList)}</span>
							</a>
						</li>
						<li class="item">
							<a class="link_summary_board"> 
								<i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure">${fn:length(doneList)}</span>
							</a>
						</li>
						<li class="item">
							<a class="link_summary_board"> 
								<i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure">${fn:length(cancelList)}</span>
							</a>
						</li>
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<c:if test="${fn:length(doingList)+ fn:length(doneList) + fn:length(cancelList) > 0}">
					<ul class="list_cards">
						<!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
						<c:if test="${fn:length(doingList) > 0}">
						<li class="card confirmed" id="cardConfirmed">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i> <span class="tit">예약 확정</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
						</c:if>
						<c:if test="${fn:length(doneList) > 0}">
						<li class="card used"  id="cardUsed">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i> <span class="tit">이용 완료</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
						</c:if>
						<c:if test="${fn:length(cancelList) > 0}">
						<li class="card used cancel" id="cardUsedCancel">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_cancel"></i> <span class="tit">취소된 예약</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
						</c:if>
					</ul>
					</c:if>
				</div>
				<!--// 내 예약 리스트 -->
				
				<!-- 예약 리스트 없음 -->
				<c:if test="${fn:length(doingList)+ fn:length(doneList) + fn:length(cancelList) == 0}">
				<div class="err">
					<i class="spr_book ico_info_nolist"></i>
					<h1 class="tit">예약 리스트가 없습니다</h1>
				</div>
				<!--// 예약 리스트 없음 -->
				</c:if>
			</div>
		</div>
		<hr>
	</div>
	<footer>
		<div class="gototop">
			<a class="lnk_top" onclick="window.scrollTo(0,0);"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
	<script>
	document.addEventListener("DOMContentLoaded", ready);
	var doingList = [];
	var doneList = [];
	var cancelList=[];
	function makeTemplate(list, targetClass) {
		const templateHTML = document.querySelector("#reserveArticle").innerText;
		const reviewBtnTemplate = document.querySelector("#reviewBtnTemplate").innerText;
		const cancelBtnTemplate = document.querySelector("#cancelBtnTemplate").innerText;
		const bindTemplate = Handlebars.compile(templateHTML);
		const bindReviewBtnTemplate = Handlebars.compile(reviewBtnTemplate);
		const bindCancelBtnTemplate = Handlebars.compile(cancelBtnTemplate);
		
		let resultHTML = "";
		
		for(let i=0;i<list.length;i++){
			resultHTML += bindTemplate(list[i]);
		}
		if(resultHTML != "")
			document.querySelector(targetClass).innerHTML += resultHTML;
		
		resultHTML = "";
		for(let i=0;i<list.length;i++){
			if(targetClass == "#cardConfirmed"){
				resultHTML = bindCancelBtnTemplate(list[i]);
			}
			else if(targetClass == "#cardUsed"){
				resultHTML = bindReviewBtnTemplate(list[i]);
			}
			document.querySelector(targetClass).querySelectorAll(".booking_cancel")[i].innerHTML = resultHTML;
		}
	}
	function popupClose(){
		document.querySelector(".popup_booking_wrapper").remove();
	}
	function cancelReserve(id){
		const findCancelReserve = doingList.find(element => element.id == id);
		const templateHTML = document.querySelector("#cancelPopupTemplate").innerText;
		const bindTemplate = Handlebars.compile(templateHTML);
		let resultHTML = bindTemplate(findCancelReserve);
		
		document.querySelector(".ct").innerHTML += resultHTML;
	}
	function ready(){
		
		<c:forEach var="doing" items="${doingList}" varStatus="num">
			doingList.push({
				id: "${doing.id}",
				description : "${doing.description}",
				placeName : "${doing.placeName}",
				createDate: "${doing.createDate}",
				displayInfoId: "${doing.displayInfoId}",
				price: ${doing.price},
				myReservationList : []
			});
			<c:forEach var="myReserve" items="${doing.myTicketInfoList}">
				doingList[${num.index}].myReservationList.push({
					priceType: "${myReserve.priceType}",
					count: "${myReserve.count}"
				});
			</c:forEach>
		</c:forEach>
		<c:forEach var="done" items="${doneList}" varStatus="num">
			doneList.push({
				id: "${done.id}",
				description : "${done.description}",
				placeName : "${done.placeName}",
				createDate: "${done.createDate}",
				displayInfoId: "${done.displayInfoId}",
				price: ${done.price},
				myReservationList : []
			});
			<c:forEach var="myReserve" items="${done.myTicketInfoList}">
				doneList[${num.index}].myReservationList.push({
					priceType: "${myReserve.priceType}",
					count: "${myReserve.count}"
				});
			</c:forEach>
		</c:forEach>
		<c:forEach var="cancel" items="${cancelList}" varStatus="num">
			cancelList.push({
				id: "${cancel.id}",
				description : "${cancel.description}",
				placeName : "${cancel.placeName}",
				createDate: "${cancel.createDate}",
				displayInfoId: "${cancel.displayInfoId}",
				price: ${cancel.price},
				myReservationList : []
			});
			<c:forEach var="myReserve" items="${cancel.myTicketInfoList}">
				cancelList[${num.index}].myReservationList.push({
					priceType: "${myReserve.priceType}",
					count: "${myReserve.count}"
				});
			</c:forEach>
		</c:forEach>
		makeTemplate(doingList,"#cardConfirmed");
		makeTemplate(doneList,"#cardUsed");
		makeTemplate(cancelList,"#cardUsedCancel");
	}
	</script>
	
	<script type="rv-template" id="cancelPopupTemplate">
		<!-- 취소 팝업 -->
		<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
		<div class="popup_booking_wrapper">
			<div class="dimm_dark" style="display: block"></div>
			<div class="popup_booking refund">
				<h1 class="pop_tit">
					<span>{{description}}</span> <small class="sm">{{createDate}}</small>
				</h1>
				<div class="nomember_alert">
					<p>취소하시겠습니까?</p>
				</div>
				<div class="pop_bottom_btnarea">
					<div class="btn_gray">
						<a class="btn_bottom" onclick="popupClose();"><span>아니오</span></a>
					</div>
					<div class="btn_green">
						<a href="/reservation/cancel?id={{id}}" class="btn_bottom"><span>예</span></a>
					</div>
				</div>
				<!-- 닫기 -->
				<a class="popup_btn_close" title="close" onclick="popupClose();"> <i class="spr_book2 ico_cls"></i>
				</a>
				<!--// 닫기 -->
			</div>
		</div>
		<!--// 취소 팝업 -->
	</script>
	
	<script type="rv-template" id="reserveArticle">
		<article class="card_item">
			<a href="#" class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					<div class="middle">
						<div class="card_detail">
							<em class="booking_number">No.{{id}}</em>
							<h4 class="tit">{{description}}</h4>
							<ul class="detail">
								<li class="item"><span class="item_tit">일정</span> <em class="item_dsc"> {{createDate}} </em></li>
								<li class="item"><span class="item_tit">내역</span> <em class="item_dsc"> 
									{{#each myReservationList}}
										{{priceType}} {{count}}개 /
									{{/each}}
									</em>
								</li>
								<li class="item"><span class="item_tit">장소</span> <em class="item_dsc"> {{placeName}} </em></li>
								<li class="item"><span class="item_tit">업체</span> <em class="item_dsc"> 업체명이 없습니다. </em></li>
							</ul>
							<div class="price_summary">
								<span class="price_tit">결제 예정금액</span> 
								<em class="price_amount"> 
									<span>{{price}}</span> <span class="unit">원</span>
								</em>
							</div>
							<div class="booking_cancel">
							</div>
						</div>
					</div>
					<div class="right"></div>
				</div>
				<div class="card_footer">
					<div class="left"></div>
					<div class="middle"></div>
					<div class="right"></div>
				</div>
			</a>
		</article>
	</script>
	
	<script type="rv-template" id="reviewBtnTemplate">
		<a href="/reservation/reviewWrite?id={{displayInfoId}}"><button class="btn">
			<span>예매자 리뷰 남기기</span>
		</button></a>
	</script>
	
	<script type="rv-template" id="cancelBtnTemplate">
		<button class="btn" onclick="cancelReserve({{id}})">
			<span>취소</span>
		</button>
	</script>
</body>

</html>