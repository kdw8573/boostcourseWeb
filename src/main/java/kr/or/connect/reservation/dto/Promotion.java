package kr.or.connect.reservation.dto;

public class Promotion {
	private Long id;
	private Long productId;
	private String saveFileName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", saveFileName=" + saveFileName + "]";
	}


}
