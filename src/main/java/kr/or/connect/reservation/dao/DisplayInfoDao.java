package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DBSqls.*;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfo;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);		
	}
	
	public int insertReserveInfo(DisplayInfo reserveInfo) {
		final KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("productId", reserveInfo.getProductId())
																.addValue("id",reserveInfo.getId())
																.addValue("name",reserveInfo.getName())
																.addValue("tel",reserveInfo.getTel())
																.addValue("email",reserveInfo.getEmail());
		jdbc.update(INSERT_RESERVE_INFO, params, holder, new String[] {"GENERATED_ID" });
		Number generatedId = holder.getKey();
		return generatedId.intValue();
	}
	
	public int getReserveId(DisplayInfo reserveInfo) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", reserveInfo.getId());
		params.put("email", reserveInfo.getEmail());
		return jdbc.queryForObject(SELECT_RESERVE_ID, params, Integer.class);
	}
	
	public void insertReserveTicket(Integer infoId, Integer priceId, Integer count) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationInfoId",infoId);
		params.put("productPriceId", priceId);
		params.put("count",count);
		jdbc.update(INSERT_RESERVE_TICKET, params);
	}
}
