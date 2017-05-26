package kr.co.sist.movie.vo;

public class UserResYesVO {
	private String id, name, seatNum;

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

	public String getSeatNum() {
		return seatNum;
	}
  
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	@Override
	public String toString() {
		return "UserResYesVO [id=" + id + ", name=" + name + ", seatNum=" + seatNum + "]";
	}
	
	
}
