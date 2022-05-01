package kr.or.connect.reservation.dto;

public class Comment {
	private String reservationDate;
	private String reservationName;
	private double score;
	private String comment;
	private String saveFileName;
	private int productId;
	private int id;
	
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
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	@Override
	public String toString() {
		return "Comment [reservationDate=" + reservationDate + ", reservationName=" + reservationName + ", score="
				+ score + ", comment=" + comment + ", saveFileName=" + saveFileName + ", productId=" + productId
				+ ", id=" + id + "]";
	}
	
	
}
