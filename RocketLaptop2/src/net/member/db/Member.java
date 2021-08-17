package net.member.db;

public class Member {
	private String user_id; // 회원 아이디
	private String user_password; // 회원 비밀번호
	private String user_name; // 회원 이름
	private int	   user_birthdate; // 회원 생년월일
	private String user_gender; // 회원 성별
	private String user_email; // 회원 이메일
	private String user_phone; // 회원 전화번호
	private String user_address1; // 회원 우편번호
	private String user_address2; // 회원 주소
	private String user_memberfile; // 회원 프로필 파일
	private String user_joindate; // 회원 가입날짜
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_password() {
		return user_password;
	}
	
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_birthdate() {
		return user_birthdate;
	}

	public void setUser_birthdate(int user_birthdate) {
		this.user_birthdate = user_birthdate;
	}

	public String getUser_gender() {
		return user_gender;
	}
	
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
	public String getUser_address1() {
		return user_address1;
	}

	public void setUser_address1(String user_address1) {
		this.user_address1 = user_address1;
	}

	public String getUser_address2() {
		return user_address2;
	}
	
	public void setUser_address2(String user_address2) {
		this.user_address2 = user_address2;
	}
	
	public String getUser_memberfile() {
		return user_memberfile;
	}
	
	public void setUser_memberfile(String user_memberfile) {
		this.user_memberfile = user_memberfile;
	}

	public String getUser_joindate() {
		return user_joindate;
	}

	public void setUser_joindate(String user_joindate) {
		this.user_joindate = user_joindate;
	}
	
}	