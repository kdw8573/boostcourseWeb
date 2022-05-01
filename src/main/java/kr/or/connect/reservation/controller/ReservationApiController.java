package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping(path="/api")
public class ReservationApiController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PromotionService promotionService;
	
	@GetMapping(path="/products")
	public Map<String, Object> list(@RequestParam(name="start", required=false, defaultValue="0") int start,
					@RequestParam(name = "category", required = false, defaultValue = "0") int category) {
		
		List<Product> list = null;
		int count = productService.getCount();
		
		if(category == 0)
			list = productService.getAllProducts(start);
		else 
			list = productService.getCategoryProducts(start, category);
		
		Map<String, Object> map = new HashMap<>();
		map.put("items", list);
		map.put("totalCount", count);
		
		return map;
	}
	
	@GetMapping(path="/categories")
	public Map<String, Object> category() {
		int count = 0;
		List<Category> list = categoryService.getCategories();
		Map<String, Object> map = new HashMap<>();
		int len = list.size();
		
		for(int i = 0; i<len ;i++) {
			count = categoryService.getCategoryCount(list.get(i).getId());
			list.get(i).setCount((long) count);
		}
		map.put("items", list);
		return map;
	}
	
	@GetMapping(path="/promotions")
	public Map<String, Object> promotion() {
		List<Promotion> list = promotionService.getPromotions();
		Map<String, Object> map = new HashMap<>();
		
		map.put("items", list);
		return map;
	}
}
