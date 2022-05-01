package kr.or.connect.reservation.dto;

public class TicketInfo {
	private String priceType;
	private Integer price;
	private Double discountRate;
	private Integer id;
	private Integer count;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Reservation [priceType=" + priceType + ", price=" + price + ", discountRate=" + discountRate + ", id="
				+ id + ", count=" + count + "]";
	}
	
	
}
