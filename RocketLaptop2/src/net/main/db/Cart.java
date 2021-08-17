package net.main.db;

public class Cart {
	private int cart_num;
	private String product_code;
	private String user_id;
	private int cart_stock;
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
	
	public int getCart_stock() {
		return cart_stock;
	}
	
	public void setCart_stock(int cart_stock) {
		this.cart_stock = cart_stock;
	}
	
	public String getAdddate() {
		return adddate;
	}
	
	public void setAdddate(String adddate) {
		this.adddate = adddate.substring(0, 10);
	}
	
}
