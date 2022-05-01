package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category;

public interface CategoryService {
	public int getCategoryCount(Integer category);
	public List<Category> getCategories();
}
