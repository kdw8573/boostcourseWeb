package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.TicketInfo;

public interface TicketInfoService {
	public List<TicketInfo> getTicketInfoList(Integer id);
	public int getTicketNum(Integer id);
	public List<TicketInfo> getMyTicketInfoList(int id);
}
