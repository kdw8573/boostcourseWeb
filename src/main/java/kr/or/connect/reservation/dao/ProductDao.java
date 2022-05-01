package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;

import static kr.or.connect.reservation.dao.DBSqls.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);		
	}
	
	public List<Product> selectAllProducts(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_All_PRODUCTS, params, rowMapper);
	}
	
	public List<Product> selectCategoryProducts(Integer start, Integer category, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("category", category);
		params.put("limit", limit);
		return jdbc.query(SELECT_CATEGORY_PRODUCTS, params, rowMapper);
	}
	
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
	
	public Product selectDetailProduct(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_DETAIL_PRODUCT, params, rowMapper);
	}
	
	public List<String> selectDetailProductImage(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForList(SELECT_DETAIL_PRODUCT_IMAGE, params, String.class);
	}
	
	public String selectDetailProductMap(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_DETAIL_PRODUCT_MAP, params, String.class);
	}

}
