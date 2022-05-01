package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getAllProducts(Integer start);
	public List<Product> getCategoryProducts(Integer start, Integer category);
	public int getCount();
	public Product getDetailProduct(Integer id);
	public List<String> getDetailProductImage(Integer id);
	public String getDetailProductMap(Integer id);
}

