package net.main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.admin.db.Product;

public class OrderDAO {
private DataSource ds;
	
	public OrderDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public int orderInsert(Order order, String cartNumArr) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into order_tb (order_num, user_id, order_name, "
				   + "user_address1, user_address2, user_address3, order_phone, "
				   + "order_totalprice, order_payment) "
				   + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		List<OrderDetail> OrderDeList = new ArrayList<OrderDetail>();
		int result = 0;
		try{
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getOrder_num());
			pstmt.setString(2, order.getUser_id());
			pstmt.setString(3, order.getOrder_name());
			pstmt.setString(4,  order.getUser_address1());
			pstmt.setString(5,  order.getUser_address2());
			pstmt.setString(6,  order.getUser_address3());
			pstmt.setString(7,  order.getOrder_phone());
			pstmt.setInt(8,  order.getOrder_totalprice());
			pstmt.setString(9, order.getOrder_payment());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into order_detail "
				+ "(order_de_num, order_num, product_code, order_de_count) "
				+ "select order_detail_seq.nextval, ?, product_code, order_de_count "
				+ "from cart "
				+ "where user_id = ?"
				+ "and cart_num in (" + cartNumArr + ")";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getOrder_num());
			pstmt.setString(2, order.getUser_id());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "select *"
				+ "from order_detail "
				+ "where order_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getOrder_num());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct_code(rs.getString("product_code"));
				orderDetail.setOrder_de_count(rs.getInt("order_de_count"));
				OrderDeList.add(orderDetail);
			}
			pstmt.close();
			
			sql = "update product "
				+ "set product_sales = product_sales + ? "
				+ "where product_code = ?";
			for(OrderDetail od : OrderDeList) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, od.getOrder_de_count());
				pstmt.setString(2, od.getProduct_code());
				pstmt.executeUpdate();
				pstmt.close();
			}
			
			sql = "delete cart "
				+ "where user_id = ?"
				+ "and cart_num in (" + cartNumArr + ")";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getUser_id());
			result = pstmt.executeUpdate();
			if(result == 0) {
				con.rollback();
			}else {
				con.commit();
			}
			
		}catch(Exception ex) {
			System.out.println("orderInsert() 에러 : " + ex);
			if(con != null) {
				try{
					con.rollback();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.setAutoCommit(true);
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	} // orderInsert() end
	
	public int orderInsert(Order order, String product_code, int order_de_count) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into order_tb (order_num, user_id, order_name, "
				   + "user_address1, user_address2, user_address3, order_phone, "
				   + "order_totalprice, order_payment) "
				   + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int result = 0;
		try{
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getOrder_num());
			pstmt.setString(2, order.getUser_id());
			pstmt.setString(3, order.getOrder_name());
			pstmt.setString(4,  order.getUser_address1());
			pstmt.setString(5,  order.getUser_address2());
			pstmt.setString(6,  order.getUser_address3());
			pstmt.setString(7,  order.getOrder_phone());
			pstmt.setInt(8,  order.getOrder_totalprice());
			pstmt.setString(9, order.getOrder_payment());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into order_detail "
				+ "(order_de_num, order_num, product_code, order_de_count) "
				+ "values(order_detail_seq.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getOrder_num());
			pstmt.setString(2, product_code);
			pstmt.setInt(3, order_de_count);
			result = pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update product "
				+ "set product_sales = product_sales + ? "
				+ "where product_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_de_count);
			pstmt.setString(2, product_code);
			result = pstmt.executeUpdate();
			if(result == 0) {
				con.rollback();
			}else {
				con.commit();
			}
			
		}catch(Exception ex) {
			System.out.println("orderInsert() 에러 : " + ex);
			if(con != null) {
				try{
					con.rollback();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.setAutoCommit(true);
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	} // orderInsert() end

	public List<Order> getOrderList(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select order_num, order_name, user_address1, user_address2, "
				   + "user_address3, order_phone, order_totalprice, order_payment,  order_delivery "
				   + "from order_tb "
				   + "where user_id = ?";
		
		List<Order> list = new ArrayList<Order>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrder_num(rs.getString("order_num"));
				order.setOrder_name(rs.getString("order_name"));
				order.setUser_address1(rs.getString("user_address1"));
				order.setUser_address2(rs.getString("user_address2"));
				order.setUser_address3(rs.getString("user_address3"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_totalprice(rs.getInt("order_totalprice"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_delivery(rs.getString("order_delivery"));
				list.add(order);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderList() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	} // getOrderList() end
	
	public List<Order> getOrderList(String user_id, int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * " 
					+"from (select rownum rnum, o.* "
					+ 	   "from (select order_num, user_id, order_name, user_address1, user_address2, user_address3, "
					+	  		        "order_phone, order_totalprice, order_payment, order_delivery, order_date " 
					+	         "from order_tb " 
					+	         "where user_id = ? " 
					+	         "order by order_date desc) o "
					+ 	  ") " 
					+"where rnum >= ? and rnum <= ?";
		
		List<Order> list = new ArrayList<Order>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrder_num(rs.getString("order_num"));
				order.setOrder_name(rs.getString("order_name"));
				order.setUser_address1(rs.getString("user_address1"));
				order.setUser_address2(rs.getString("user_address2"));
				order.setUser_address3(rs.getString("user_address3"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_totalprice(rs.getInt("order_totalprice"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_delivery(rs.getString("order_delivery"));
				order.setOrder_date(rs.getString("order_date"));
				list.add(order);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderList() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	} // getOrderList() end
	
	public Order getOrder(String order_num, String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select order_num, user_id, order_name, user_address1, user_address2, "
				   + "user_address3, order_phone, order_totalprice, order_payment,  order_delivery, order_date "
				   + "from order_tb "
				   + "where order_num = ?"
				   + "and user_id = ?";
		
		Order order = new Order();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_num);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order.setOrder_num(rs.getString("order_num"));
				order.setUser_id(rs.getString("user_id"));
				order.setOrder_name(rs.getString("order_name"));
				order.setUser_address1(rs.getString("user_address1"));
				order.setUser_address2(rs.getString("user_address2"));
				order.setUser_address3(rs.getString("user_address3"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_totalprice(rs.getInt("order_totalprice"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_delivery(rs.getString("order_delivery"));
				order.setOrder_date(rs.getString("order_date"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderList() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return order;
	} // getOrderList() end

	public int getOrderListCount(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from order_tb where user_id = ?";
		
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderListCount() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	} // getOrderListCount() end

	public List<OrderList> getOrderDetailList(String order_num, String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select o.order_num, o.user_id, o.order_name, o.user_address1, o.user_address2, o.user_address3, " 
					+	   " o.order_phone, o.order_totalprice, o.order_payment, o.order_delivery, o.order_date, "
					+	   " d.order_de_num, d.product_code, d.order_de_count, p.product_name, p.product_image, p.product_price " 
					+"from order_tb o "
					+		" inner join order_detail d "
					+			" on o.order_num = d.order_num "
					+		" inner join product p "
					+			" on p.product_code = d.product_code " 
					+"where o.user_id = ? "
					+"and o.order_num = ?";
		
		List<OrderList> list = new ArrayList<OrderList>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, order_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderList orderlist = new OrderList();
				orderlist.setOrder_num(rs.getString("order_num"));
				orderlist.setUser_id(rs.getString("user_id"));
				orderlist.setOrder_name(rs.getString("order_name"));
				orderlist.setUser_address1(rs.getString("user_address1"));
				orderlist.setUser_address2(rs.getString("user_address2"));
				orderlist.setUser_address3(rs.getString("user_address3"));
				orderlist.setOrder_phone(rs.getString("order_phone"));
				orderlist.setOrder_totalprice(rs.getInt("order_totalprice"));
				orderlist.setOrder_payment(rs.getString("order_payment"));
				orderlist.setOrder_delivery(rs.getString("order_delivery"));
				orderlist.setOrder_date(rs.getString("order_date"));
				orderlist.setOrder_de_num(rs.getInt("order_de_num"));
				orderlist.setProduct_code(rs.getString("product_code"));
				orderlist.setOrder_de_count(rs.getInt("order_de_count"));
				orderlist.setProduct_name(rs.getString("product_name"));
				orderlist.setProduct_image(rs.getString("product_image"));
				orderlist.setProduct_price(rs.getInt("product_price"));
				list.add(orderlist);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderDetailList() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	} // getOrderDetailList() end

	public List<Order> getOrderList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select order_num, order_name, user_address1, user_address2, "
				   + "user_address3, order_phone, order_totalprice, order_payment,  order_delivery "
				   + "from order_tb";
		
		List<Order> list = new ArrayList<Order>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrder_num(rs.getString("order_num"));
				order.setOrder_name(rs.getString("order_name"));
				order.setUser_address1(rs.getString("user_address1"));
				order.setUser_address2(rs.getString("user_address2"));
				order.setUser_address3(rs.getString("user_address3"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_totalprice(rs.getInt("order_totalprice"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_delivery(rs.getString("order_delivery"));
				list.add(order);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderList() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	} // getOrderList() end

	public int getOrderListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from order_tb";
		
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderListCount() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	} // getOrderListCount() end

	public List<Order> getOrderList(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * "
					+"from (select rownum rnum, o.* "
					+ 	   "from (select order_num, user_id, order_name, user_address1, user_address2, user_address3, " 
					+	  		  	    "order_phone, order_totalprice, order_payment, order_delivery, order_date "
					+	 		 "from order_tb "
					+	 		 "order by order_date desc) o "
					+ 	  ") "
					+"where rnum >= ? and rnum <= ?";
		
		List<Order> list = new ArrayList<Order>();
		
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrder_num(rs.getString("order_num"));
				order.setUser_id(rs.getString("user_id"));
				order.setOrder_name(rs.getString("order_name"));
				order.setUser_address1(rs.getString("user_address1"));
				order.setUser_address2(rs.getString("user_address2"));
				order.setUser_address3(rs.getString("user_address3"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_totalprice(rs.getInt("order_totalprice"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_delivery(rs.getString("order_delivery"));
				order.setOrder_date(rs.getString("order_date"));
				list.add(order);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderList() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	} // getOrderList() end

	public int getOrderListCount(String field, String value) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int o = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) "
						+"from (select rownum rnum, order_num, user_id, order_name, user_address1, user_address2, user_address3, " 
						+	   "order_phone, order_totalprice, order_payment, order_delivery, order_date "
						+	   "from order_tb "
						+ 	   "where " + field + " like ?"
						+	   "order by order_date desc) ";
			System.out.println(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + value + "%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				o = rs.getInt(1);
				System.out.println(o);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getProductListCount() 에러: " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return o;
	} // getOrderListCount() end

	public List<Order> getOrderList(String field, String value, int page, int limit) {
		List<Order> list = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
						+"from (select rownum rnum, order_num, user_id, order_name, user_address1, user_address2, user_address3, "
						+	  		 " order_phone, order_totalprice, order_payment, order_delivery, order_date "
						+	   "from order_tb "
						+	   "where " + field + " like ? "
						+	   "order by order_date desc) " 
						+"where rnum >= ? and rnum <= ?";
			System.out.println(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + value + "%");
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrder_num(rs.getString("order_num"));
				order.setUser_id(rs.getString("user_id"));
				order.setOrder_name(rs.getString("order_name"));
				order.setUser_address1(rs.getString("user_address1"));
				order.setUser_address2(rs.getString("user_address2"));
				order.setUser_address3(rs.getString("user_address3"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_totalprice(rs.getInt("order_totalprice"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_delivery(rs.getString("order_delivery"));
				order.setOrder_date(rs.getString("order_date"));
				list.add(order);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderList() 에러: " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return list;
	} // getOrderList() end

	public int getOrderDeliveryListCount(String delivery) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) "
				   + "from order_tb "
				   + "where order_delivery = ?";
		
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, delivery);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderDeliveryListCount() 에러 : " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	} // getOrderDeliveryListCount() end

	public List<Order> getOrderDeliveryList(String delivery, int page, int limit) {
		List<Order> list = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
						+"from (select rownum rnum, order_num, user_id, order_name, user_address1, user_address2, user_address3, "
						+	  		 " order_phone, order_totalprice, order_payment, order_delivery, order_date "
						+	   "from order_tb "
						+	   "where order_delivery like ? "
						+	   "order by order_date desc) " 
						+"where rnum >= ? and rnum <= ?";
			System.out.println(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + delivery + "%");
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrder_num(rs.getString("order_num"));
				order.setUser_id(rs.getString("user_id"));
				order.setOrder_name(rs.getString("order_name"));
				order.setUser_address1(rs.getString("user_address1"));
				order.setUser_address2(rs.getString("user_address2"));
				order.setUser_address3(rs.getString("user_address3"));
				order.setOrder_phone(rs.getString("order_phone"));
				order.setOrder_totalprice(rs.getInt("order_totalprice"));
				order.setOrder_payment(rs.getString("order_payment"));
				order.setOrder_delivery(rs.getString("order_delivery"));
				order.setOrder_date(rs.getString("order_date"));
				list.add(order);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getOrderDeliveryList() 에러: " + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return list;
	} // getOrderDeliveryList() end

	public int orderDeliveryUpdate(String order_num, String user_id, String deliveryStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update order_tb "
				   + "set order_delivery = ? "
				   + "where user_id = ? "
				   + "and order_num = ?";
		
		int result = 0;
		try{
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, deliveryStatus);
			pstmt.setString(2, user_id);
			pstmt.setString(3, order_num);
			result = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("orderDeliveryUpdate() 에러 : " + ex);
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	} // orderDeliveryUpdate() end
	
}
