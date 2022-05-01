package kr.or.connect.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.dto.MyReservation;
import kr.or.connect.reservation.dto.TicketInfo;
import kr.or.connect.reservation.service.MyReservationService;
import kr.or.connect.reservation.service.TicketInfoService;

@Controller
public class MyReservationPageController {
	@Autowired
	MyReservationService myReservationService;
	
	@Autowired
	TicketInfoService reservationService;
	
	public List<MyReservation> lineBreakDelete2(List<MyReservation> list){
		MyReservation product = null;
		for (int i = 0; i < list.size(); i++) {
			product = (MyReservation) list.get(i);
			list.get(i).setDescription(product.getDescription().replace("\n", ""));
			list.get(i).setPlaceName(product.getPlaceName().replace("\n", ""));
		}
		return list;
	}
	
	public List<MyReservation> setMyTicketInfoList(List<MyReservation> list){	
		List<TicketInfo> ticketList = null; 
		int price = 0;
		for(int i=0;i<list.size();i++) {
			ticketList = reservationService.getMyTicketInfoList(list.get(i).getId());
			list.get(i).setMyTicketInfoList(ticketList);
			for(int j=0;j<ticketList.size();j++) {
				price += ticketList.get(j).getPrice() * ticketList.get(j).getCount();
			}
			list.get(i).setPrice(price);
			price = 0;
		}
		return list;
	}
	@GetMapping(path="/myreservation")
	public String myReservationPage(HttpSession session, ModelMap model) {
		String email = (String) session.getAttribute("email");
		
		if(email == null || email.equals("")) {
			return "login";
		}
		
		List<MyReservation> doingList = myReservationService.getMyReservationDoing(email);
		List<MyReservation> doneList = myReservationService.getMyReservationDone(email);
		List<MyReservation> cancelList = myReservationService.getMyReservationCancel(email);
		
		lineBreakDelete2(doingList);
		lineBreakDelete2(doneList);
		lineBreakDelete2(cancelList);
		
		doingList = setMyTicketInfoList(doingList);
		doneList = setMyTicketInfoList(doneList);
		cancelList = setMyTicketInfoList(cancelList);
		
		model.put("doingList", doingList);
		model.put("doneList", doneList);
		model.put("cancelList", cancelList);
		
		return "myreservation";
	}
	
	@GetMapping(path="/cancel")
	public String cancelReserve(@RequestParam(name = "id", required = true) Long id,ModelMap model) {
		myReservationService.updateMyReservationCancel(id);
		return "redirect:myreservation";
	}
}
