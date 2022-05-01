package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.TicketInfo;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.TicketInfoService;

@Controller
public class ReservePageController {
	@Autowired
	ProductService productService;
	
	@Autowired
	TicketInfoService reservationService;
	
	@Autowired
	DisplayInfoService displayInfoService;
	
	@GetMapping("/reserve")
	public String reservePage(@RequestParam(name = "id", required = true) int id, ModelMap model) {
		Product product = productService.getDetailProduct(id);		
		List<TicketInfo> reservationList = reservationService.getTicketInfoList(id);
		Integer ticketNum = 300 - reservationService.getTicketNum(id);
		
		model.put("detailProduct", product);
		model.put("reservationList", reservationList);
		model.put("ticketNum", ticketNum);
		
		return "reserve";
	}
	
	@PostMapping(path="/reserve")
	public String insertReserveInfo(@ModelAttribute DisplayInfo reserveInfo) {
		int id = displayInfoService.insertReserveInfo(reserveInfo);
		for(int i=0;i<reserveInfo.getTicketsIdList().size();i++) {
			displayInfoService.insertReserveTicket(id, reserveInfo.getTicketsIdList().get(i), reserveInfo.getTicketsNumList().get(i));
		}
		return "redirect:main";
	}
}
