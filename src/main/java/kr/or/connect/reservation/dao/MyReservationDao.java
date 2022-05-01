package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DBSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.MyReservation;

@Repository
public class MyReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MyReservation> rowMapper = BeanPropertyRowMapper.newInstance(MyReservation.class);
	
	public MyReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);		
	}
	
	public List<MyReservation> selectMyReservationDoing(String email) {
		Map<String, Object> params = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_MYRESERVE_DOING, params, rowMapper);
	}
	
	public List<MyReservation> selectMyReservationDone(String email) {
		Map<String, Object> params = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_MYRESERVE_DONE, params, rowMapper);
	}
	
	public List<MyReservation> selectMyReservationCancel(String email) {
		Map<String, Object> params = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_MYRESERVE_CANCEL, params, rowMapper);
	}
	
	public int updateMyReservationCancel(Long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		return jdbc.update(UPDATE_MYRESERVE_CANCEL, params);
	}
}
