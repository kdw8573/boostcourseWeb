package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.MyReservation;

public interface MyReservationService {
	public List<MyReservation> getMyReservationDoing(String email);
	public List<MyReservation> getMyReservationDone(String email);
	public List<MyReservation> getMyReservationCancel(String email);
	public int updateMyReservationCancel(Long id);
}
