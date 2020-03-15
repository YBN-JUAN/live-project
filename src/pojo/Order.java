package pojo;

public class Order {

	private int id;
	private boolean opening;
	private int maskNum;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isOpening() {
		return opening;
	}
	
	public void setOpening(boolean opening) {
		this.opening = opening;
	}
	
	public int getMaskNum() {
		return maskNum;
	}
	
	public void setMaskNum(int maskNum) {
		this.maskNum = maskNum;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", opening=" + opening + ", maskNum="
				+ maskNum + "]";
	}
	
}
