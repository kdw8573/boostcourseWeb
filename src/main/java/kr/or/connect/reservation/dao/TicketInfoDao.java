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

import kr.or.connect.reservation.dto.TicketInfo;

@Repository
public class TicketInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<TicketInfo> rowMapper = BeanPropertyRowMapper.newInstance(TicketInfo.class);
	
	public TicketInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);		
	}
	
	public List<TicketInfo> selectTicketInfoList(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.query(SELECT_TICKET_INFO, params, rowMapper);
	}
	
	public int selectTicketNum(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_TICKET_NUM, params, Integer.class);
	}
	
	public List<TicketInfo> selectMyTicketInfoList(int id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.query(SELECT_MYRESERVE_TICKET, params, rowMapper);
	}
	
}
