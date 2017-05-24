package kr.co.sist.movie.vo;

public class UserResYesVO {
	private String id, name, seatNum1, seatNum2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeatNum1() {
		return seatNum1;
	}

	public void setSeatNum1(String seatNum1) {
		this.seatNum1 = seatNum1;
	}

	public String getSeatNum2() {
		return seatNum2;
	}
  
	public void setSeatNum2(String seatNum2) {
		this.seatNum2 = seatNum2;
	}

	@Override
	public String toString() {
		return "UserResYesVO [id=" + id + ", name=" + name + ", seatNum1=" + seatNum1 + ", seatNum2=" + seatNum2 + "]";
	}

	
	
	
}
