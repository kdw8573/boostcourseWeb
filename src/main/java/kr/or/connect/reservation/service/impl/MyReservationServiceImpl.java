package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.MyReservationDao;
import kr.or.connect.reservation.dto.MyReservation;
import kr.or.connect.reservation.service.MyReservationService;

@Service
public class MyReservationServiceImpl implements MyReservationService{
	@Autowired
	MyReservationDao myReservationDao;
	
	@Override
	@Transactional
	public List<MyReservation> getMyReservationDoing(String email) {
		return myReservationDao.selectMyReservationDoing(email);
	}

	@Override
	@Transactional
	public List<MyReservation> getMyReservationDone(String email) {
		return myReservationDao.selectMyReservationDone(email);
	}

	@Override
	@Transactional
	public List<MyReservation> getMyReservationCancel(String email) {
		return myReservationDao.selectMyReservationCancel(email);
	}

	@Override
	@Transactional(readOnly=false)
	public int updateMyReservationCancel(Long id) {
		return myReservationDao.updateMyReservationCancel(id);
	}
	
}
