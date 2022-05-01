package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.ProductService;

@Controller
public class ReviewPageController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/review")
	public String reviewPage(@RequestParam(name = "id", required = true) int id, ModelMap model) {
		Product product = productService.getDetailProduct(id);
		List<Comment> commentList = commentService.getDetailComment(id);
		Map<String, String> commentMapInfo = commentService.getDetailCommentScoreAvgLength(id);
		
		model.put("detailProduct", product);
		model.put("detailCommentList", commentList);
		model.put("scoreAvg", commentMapInfo.get("scoreAvg"));
		model.put("commentLength", commentMapInfo.get("length"));
		
		return "review";
	}
}
