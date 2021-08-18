package net.main.db;

public class OrderDetail {
	private int order_de_num;
	private String order_num;
	private String product_code;
	private int cart_stock;
	private String order_delivery;
	
	public int getOrder_de_num() {
		return order_de_num;
	}
	
	public void setOrder_de_num(int order_de_num) {
		this.order_de_num = order_de_num;
	}
	
	public String getOrder_num() {
		return order_num;
	}
	
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
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
	
	public String getOrder_delivery() {
		return order_delivery;
	}
	
	public void setOrder_delivery(String order_delivery) {
		this.order_delivery = order_delivery;
	}
}
