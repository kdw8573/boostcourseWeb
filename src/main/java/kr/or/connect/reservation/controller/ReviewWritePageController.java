package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.DisplayInfoService;

@Controller
public class ReviewWritePageController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	DisplayInfoService reserveInfoService;
	
	@GetMapping(path="/reviewWrite")
	public String reviewWrite(@RequestParam(name = "id", required = true) int id, ModelMap model) {
		Product product = productService.getDetailProduct(id);
		product.setDescription(product.getDescription().replace("\n", ""));
		model.put("product",product);
		
		return "reviewWrite";
	}
	
	@PostMapping("/uploadReview")
	public String uploadReview(@RequestParam(name = "file", required = false) MultipartFile file, @ModelAttribute DisplayInfo reserveInfo, 
								@ModelAttribute Comment comment, HttpSession session) {
		String email = (String) session.getAttribute("email");
		reserveInfo.setEmail(email);
		int reserveInfoId = reserveInfoService.getReserveId(reserveInfo);
		
		if(file != null) {
			commentService.insertMyReserveCommentImage(file, comment, reserveInfoId);
		}
		else {
			commentService.insertMyReserveComment(comment, reserveInfoId);
		}
		return "redirect:myreservation";
	}
}
