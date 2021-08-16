package net.member.db;

public class Member {
	private String user_id			;	//1
	private String user_password	;	//2 
	private String user_name		;	//4
	private int	   user_datebirth		;   //5
	private String user_gender		;	//6
	private String user_email		;	//7
	private String user_phone		;	//8
	private int    user_address1	;	//9
	private String user_address2	;	//10
	private String memberfile		;
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
	public int getUser_datebirth() {
		return user_datebirth;
	}
	public void setUser_datebirth(int user_datebirth) {
		this.user_datebirth = user_datebirth;
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
	public int getUser_address1() {
		return user_address1;
	}
	public void setUser_address1(int user_address1) {
		this.user_address1 = user_address1;
	}
	public String getUser_address2() {
		return user_address2;
	}
	public void setUser_address2(String user_address2) {
		this.user_address2 = user_address2;
	}
	public String getMemberfile() {
		return memberfile;
	}
	public void setMemberfile(String memberfile) {
		this.memberfile = memberfile;
	}
	
	
	
	
}	