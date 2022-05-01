package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.Comment;

public interface CommentService {
	public List<Comment> getDetailCommentThree(Integer id);
	public List<Comment> getDetailComment(Integer id);
	public Map<String, String> getDetailCommentScoreAvgLength(Integer id);
	public int insertMyReserveComment(Comment comment, Integer reserveInfoId);
	public void insertMyReserveCommentImage(MultipartFile file, Comment comment, Integer reserveInfoId);
}
