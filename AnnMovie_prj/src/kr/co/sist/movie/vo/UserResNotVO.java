package kr.co.sist.movie.vo;

public class UserResNotVO {
	private String id, name;
	int seatQuan;

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

	public int getSeatQuan() {
		return seatQuan;
	}

	public void setSeatQuan(int seatQuan) {
		this.seatQuan = seatQuan;
	}

	@Override
	public String toString() {
		return "UserResNotVO [id=" + id + ", name=" + name + ", seatQuan=" + seatQuan + "]";
	}
	
}
