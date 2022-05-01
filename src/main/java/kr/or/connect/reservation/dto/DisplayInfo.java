package kr.or.connect.reservation.dto;

import java.util.List;

public class DisplayInfo {
	private int productId;
	private int id;
	private String name;
	private String tel;
	private String email;
	private List<Integer> ticketsNumList;
	private List<Integer> ticketsIdList;
	
	public List<Integer> getTicketsNumList() {
		return ticketsNumList;
	}
	public void setTicketsNumList(List<Integer> ticketsNumList) {
		this.ticketsNumList = ticketsNumList;
	}
	public List<Integer> getTicketsIdList() {
		return ticketsIdList;
	}
	public void setTicketsIdList(List<Integer> ticketsIdList) {
		this.ticketsIdList = ticketsIdList;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ReserveInfo [productId=" + productId + ", id=" + id + ", name=" + name + ", tel=" + tel + ", email="
				+ email + ", ticketsNumList=" + ticketsNumList + ", ticketsIdList=" + ticketsIdList + "]";
	}
	
	
}
