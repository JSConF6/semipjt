package net.main.db;

public class OrderList {
	private int order_de_num;
	private String product_code;
	private int cart_stock;
	
	private String product_name;
	private String product_image;
	private int product_price;
	
	public int getOrder_de_num() {
		return order_de_num;
	}
	
	public void setOrder_de_num(int order_de_num) {
		this.order_de_num = order_de_num;
	}
	
	public String getProduct_code() {
		return product_code;
	}
	
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	
	public int getCart_stock() {
		return cart_stock;
	}
	
	public void setCart_stock(int cart_stock) {
		this.cart_stock = cart_stock;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public String getProduct_image() {
		return product_image;
	}
	
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
	public int getProduct_price() {
		return product_price;
	}
	
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
}
