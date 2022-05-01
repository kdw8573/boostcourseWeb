package kr.or.connect.reservation.dto;

import java.util.Date;
import java.util.List;

public class MyReservation {
	private List<TicketInfo> myTicketInfoList;
	private int id;
	private String description;
	private String placeName;
	private Date createDate;
	private int displayInfoId;
	private int price;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<TicketInfo> getMyTicketInfoList() {
		return myTicketInfoList;
	}
	public void setMyTicketInfoList(List<TicketInfo> myTicketInfoList) {
		this.myTicketInfoList = myTicketInfoList;
	}
	
	@Override
	public String toString() {
		return "MyReservation [myReservationList=" + myTicketInfoList + ", id=" + id + ", description=" + description
				+ ", placeName=" + placeName + ", createDate=" + createDate + ", displayInfoId=" + displayInfoId
				+ ", price=" + price + "]";
	}
	
}
