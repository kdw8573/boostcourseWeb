package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@Controller
public class MainPageController {
	@Autowired
	ProductService productService;
	
	public List<Product> deleteWhiteSpace(List<Product> list){
		Product product = null;
		for (int i = 0; i < list.size(); i++) {
			product = (Product) list.get(i);
			list.get(i).setDescription(product.getDescription().replace("\n", ""));
			list.get(i).setContent(product.getContent().replace("\n", ""));
			list.get(i).setPlaceName(product.getPlaceName().replace("\n", ""));
		}
		return list;
	}
	
	@GetMapping(path="/main")
	public String mainPage(ModelMap model) {
		int count = productService.getCount();
		List<Product> allProductList = productService.getAllProducts(0);

		model.addAttribute("count", count);
		model.addAttribute("list", deleteWhiteSpace(allProductList));
		model.addAttribute("limit", ProductService.LIMIT);
		return "main";

	}

	@PostMapping(path="/main")
	@ResponseBody //자바 객체를 HTTP 응답 본문의 객체로 변환
	public List<Product> loadProductListAjax(@RequestBody Map<String,Integer> map) { //HTTP 요청 본문에 담긴 값들을 자바 객체로 변환
		List<Product> productList = null;
		
		if(map.get("category") > 0) { //카테코리가 있는 경우
			productList = productService.getCategoryProducts(map.get("start"), map.get("category"));
		}
		else { //전체 상품을 불러오는 경우
			productList = productService.getAllProducts(map.get("start"));
		}
		productList = deleteWhiteSpace(productList);
		
		return productList;
	
	}
}
