package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.ProductService;

@Controller
public class DetailPageController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/detail")
	public String detailPage(@RequestParam(name = "id", required = true) int id, ModelMap model) {
		Product product = productService.getDetailProduct(id);
		String productMap = productService.getDetailProductMap(id);
		List<Comment> comment3 = commentService.getDetailCommentThree(id);
		Map<String, String> commentMapInfo = commentService.getDetailCommentScoreAvgLength(id);
		
		model.put("detailProduct", product);
		model.put("detailProductMap", productMap);
		model.put("comment3", comment3);
		model.put("scoreAvg", commentMapInfo.get("scoreAvg"));
		model.put("commentLength", commentMapInfo.get("length"));
		return "detail";
	}
	
	@PostMapping(path="/loadMainImage")
	@ResponseBody
	public List<String> getDetailProductImageAjax(@RequestBody Map<String,Integer> map) {
		List<String> imageList = productService.getDetailProductImage(map.get("id"));
		return imageList;
	}
}
