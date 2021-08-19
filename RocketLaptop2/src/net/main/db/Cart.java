package net.main.db;

public class Cart {
	private int cart_num;
	private String product_code;
	private String user_id;
	private int order_de_count;
	private String adddate;
	
	public int getCart_num() {
		return cart_num;
	}
	
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	
	public String getProduct_code() {
		return product_code;
	}
	
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public int getOrder_de_count() {
		return order_de_count;
	}

	public void setOrder_de_count(int order_de_count) {
		this.order_de_count = order_de_count;
	}
	
	public String getAdddate() {
		return adddate;
	}
	
	public void setAdddate(String adddate) {
		this.adddate = adddate.substring(0, 10);
	}
	
}
