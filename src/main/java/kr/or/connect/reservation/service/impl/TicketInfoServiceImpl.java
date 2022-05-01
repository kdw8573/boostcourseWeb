package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.TicketInfoDao;
import kr.or.connect.reservation.dto.TicketInfo;
import kr.or.connect.reservation.service.TicketInfoService;

@Service
public class TicketInfoServiceImpl implements TicketInfoService {
	@Autowired
	TicketInfoDao reservationDao;
	
	@Override
	@Transactional
	public List<TicketInfo> getTicketInfoList(Integer id) {
		List<TicketInfo> list = reservationDao.selectTicketInfoList(id);
		return list;
	}

	@Override
	@Transactional
	public int getTicketNum(Integer id) {
		return reservationDao.selectTicketNum(id);
	}
	
	@Override
	@Transactional
	public List<TicketInfo> getMyTicketInfoList(int id) {
		List<TicketInfo> list = reservationDao.selectMyTicketInfoList(id);
		return list;
	}
}
