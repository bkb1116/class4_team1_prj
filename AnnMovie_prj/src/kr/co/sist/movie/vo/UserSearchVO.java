package kr.co.sist.movie.vo;

public class UserSearchVO {
	private String name, phone, email;

//	public UserSearchVO() {
//
//	}//�⺻ ������
//	
//	
//
//	public UserSearchVO(String name, String phone, String email) {
//		super();
//		this.name = name;
//		this.phone = phone;
//		this.email = email;
//	}
//


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "UserSearchVO [name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}
	
	
}
