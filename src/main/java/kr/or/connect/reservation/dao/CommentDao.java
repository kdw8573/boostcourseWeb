package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DBSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.Comment;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);		
	}
	
	public List<Comment> selectDetailCommentThree(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.query(SELECT_DETAIL_COMMENT_THREE, params, rowMapper);
	}
	
	public List<Comment> selectDetailComment(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.query(SELECT_DETAIL_COMMENT, params, rowMapper);
	}
	
	public int insertMyReserveComment(Comment comment, Integer reserveInfoId) {
		final KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("productId",comment.getProductId())
																.addValue("reserveInfoId", reserveInfoId)
																.addValue("score", comment.getScore())
																.addValue("comment",comment.getComment());
		jdbc.update(INSERT_MYRESERVE_COMMENT, params, holder, new String[] {"GENERATED_ID" });
		Number generatedId = holder.getKey();
		return generatedId.intValue();
	}
		
	public int insertMyReserveImage(MultipartFile file) {
		final KeyHolder holder = new GeneratedKeyHolder();
		String saveFileName = "img/" + file.getOriginalFilename();
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("fileName", file.getOriginalFilename())
																.addValue("saveFileName",saveFileName)
																.addValue("contentType",file.getContentType());
		jdbc.update(INSERT_MYRESERVE_IMAGE, params, holder, new String[] {"GENERATED_ID" });
		Number generatedId = holder.getKey();
		return generatedId.intValue();
	}
	
	public void insertReserveFileInfo(Integer reserveInfoId, Integer commentId, Integer fileId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reserveInfoId", reserveInfoId);
		params.put("commentId", commentId);
		params.put("fileId", fileId);
		jdbc.update(INSERT_COMMENT_IMAGE, params);
	}
}
