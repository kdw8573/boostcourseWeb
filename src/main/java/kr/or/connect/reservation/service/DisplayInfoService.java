package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.DisplayInfo;

public interface DisplayInfoService {
	public int insertReserveInfo(DisplayInfo reserveInfo);
	public int getReserveId(DisplayInfo reserveInfo);
	public void insertReserveTicket(Integer infoId, Integer priceId, Integer count);
}
