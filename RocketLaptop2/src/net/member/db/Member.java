package net.member.db;

public class Member {
	private String user_id			;	//1
	private String user_password	;	//2 
	private String user_password1	;	//3
	private String user_name		;	//4
	private int	   user_jumin		;   //5
	private String user_gender		;	//6
	private String user_email		;	//7
	private int    user_phone		;	//8
	private int    user_address1	;	//9
	private String user_address2	;	//10
	private String memberfile		;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userid) {
		this.user_id = userid;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_password1() {
		return user_password1;
	}
	public void setUser_password1(String user_password1) {
		this.user_password1 = user_password1;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_jumin() {
		return user_jumin;
	}
	public void setUser_jumin(int user_jumin) {
		this.user_jumin = user_jumin;
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
	public int getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(int user_phone) {
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