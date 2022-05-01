package kr.or.connect.reservation.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Override
	@Transactional
	public List<Comment> getDetailCommentThree(Integer id) {
		List<Comment> list = commentDao.selectDetailCommentThree(id);
		return list;
	}
	
	public double evalScoreAvg(List<Comment> commentList) {
		double scoreAvg = 0;
		int len = commentList.size();
		for(int i=0; i<len; i++)
			scoreAvg += commentList.get(i).getScore();
		
		scoreAvg = Math.floor(scoreAvg / len *10)/10.0;
		return scoreAvg;
	}
	
	@Override
	@Transactional
	public Map<String, String> getDetailCommentScoreAvgLength(Integer id) {
		List<Comment> list = commentDao.selectDetailComment(id);
		Map<String, String> commentInfoMap = new HashMap<String,String>();
		if(list != null) {
			commentInfoMap.put("length", String.valueOf(list.size()));
			commentInfoMap.put("scoreAvg", Double.toString(evalScoreAvg(list)));
		}
		else {
			commentInfoMap.put("length", "0");
			commentInfoMap.put("scoreAvg", "0");
		}
		return commentInfoMap;
	}
	
	@Override
	@Transactional
	public List<Comment> getDetailComment(Integer id) {
		List<Comment> list = commentDao.selectDetailComment(id);
		return list;
	}
	
	@Override
	@Transactional(readOnly = false)
	public int insertMyReserveComment(Comment comment, Integer reserveInfoId) {
		return commentDao.insertMyReserveComment(comment, reserveInfoId);
	}

	@Override
	@Transactional(readOnly = false)
	public void insertMyReserveCommentImage(MultipartFile file, Comment comment, Integer reserveInfoId) {
		try (
				FileOutputStream fos = new FileOutputStream("C:/Users/김동욱/eclipse-workspace/reservation/src/main/webapp/img/" + file.getOriginalFilename());
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
		int commentId = commentDao.insertMyReserveComment(comment, reserveInfoId);
		int fileId = commentDao.insertMyReserveImage(file);
		commentDao.insertReserveFileInfo(reserveInfoId, commentId, fileId);
	}

}
