package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional
	public List<Product> getAllProducts(Integer start) {
		List<Product> list = productDao.selectAllProducts(start, ProductService.LIMIT);
		return list;
	}

	@Override
	@Transactional
	public int getCount() {
		return productDao.selectCount();
	}

	@Override
	@Transactional
	public List<Product> getCategoryProducts(Integer start, Integer category) {
		List<Product> list = productDao.selectCategoryProducts(start, category, ProductService.LIMIT);
		return list;
	}
	
	@Override
	@Transactional
	public Product getDetailProduct(Integer id) {
		Product product = productDao.selectDetailProduct(id);
		return product;
	}
	
	@Override
	@Transactional
	public List<String> getDetailProductImage(Integer id) {
		List<String> imageList = productDao.selectDetailProductImage(id);
		return imageList;
	}
	
	@Override
	@Transactional
	public String getDetailProductMap(Integer id) {
		String productMap = productDao.selectDetailProductMap(id);
		return productMap;
	};
	
	
}
