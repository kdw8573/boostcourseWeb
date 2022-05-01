package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService{
	@Autowired
	DisplayInfoDao reserveInfoDao;
	
	@Override
	@Transactional(readOnly=false)
	public int insertReserveInfo(DisplayInfo reserveInfo) {
		return reserveInfoDao.insertReserveInfo(reserveInfo);
	}
	
	@Override
	public int getReserveId(DisplayInfo reserveInfo) {
		return reserveInfoDao.getReserveId(reserveInfo);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void insertReserveTicket(Integer infoId, Integer priceId, Integer count) {
		reserveInfoDao.insertReserveTicket(infoId,priceId,count);
	}
}
