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

public class CartDAO {
	private DataSource ds;
	
	public CartDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public boolean cartInsert(Cart cart) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			con = ds.getConnection();
			
			String sql = "insert into cart(cart_num, product_code, user_id, order_de_count) "
					   + "values(cart_seq.nextval, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart.getProduct_code());
			pstmt.setString(2, cart.getUser_id());
			pstmt.setInt(3, cart.getOrder_de_count());
			
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("데이터 삽입이 완료되었습니다.");
				return true;
			}
			
		}catch(Exception ex) {
			System.out.println("cartInsert() 에러 : " + ex);
			ex.printStackTrace();
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
		return false;
	}

	public List<CartList> getCartList(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select c.cart_num, c.user_id, c.product_code, c.order_de_count, " 
					+"c.adddate, p.product_name, p.product_price, p.product_image " 
					+"from cart c, product p "
					+"where c.product_code = p.product_code "
					+"and c.user_id = ? " 
					+"order by c.cart_num";
		
		List<CartList> list = new ArrayList<CartList>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				CartList cartlist = new CartList();
				cartlist.setCart_num(rs.getInt("cart_num"));
				cartlist.setProduct_code(rs.getString("product_code"));
				cartlist.setUser_id(rs.getString("user_id"));
				cartlist.setOrder_de_count(rs.getInt("order_de_count"));
				cartlist.setAdddate(rs.getString("adddate"));
				cartlist.setProduct_name(rs.getString("product_name"));
				cartlist.setProduct_price(rs.getInt("product_price"));
				cartlist.setProduct_image(rs.getString("product_image"));
				list.add(cartlist);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getCartList() 에러 : " + ex);
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
	} // getCartList() end

	public int getCartListCount(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int c = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from cart "
					   + "where user_id = ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				c = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getCartListCount() 에러: " + ex);
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
		
		return c;
	} // getCartListCount() end

	public int cartDelete(int cart_num, String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			con = ds.getConnection();
			
			String sql = "delete cart "
					   + "where cart_num = ?"
					   + "and user_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			pstmt.setString(2, user_id);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("cartDelete() 에러 : " + ex);
			ex.printStackTrace();
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
	} // cartDelete() end

	public int CartSelectionDelete(String[] ajaxArr, String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete CART where CART_NUM in ("; 
		for(int i = 0; i < ajaxArr.length; i++) {
			sql += ajaxArr[i];
			if(i != ajaxArr.length - 1) {
				sql += ", ";
			}
		}
		sql += ") and user_id = ?";
		
		System.out.println("sql = " + sql);
		
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			result = pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("CartSelectionDelete() 에러 : " + ex);
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
	} // CartSelectionDelete() end

	public List<CartList> getCartList(String user_id, String[] cartValues) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select c.cart_num, c.user_id, c.product_code, c.order_de_count, " 
					+"c.adddate, p.product_name, p.product_price, p.product_image " 
					+"from cart c, product p "
					+"where c.product_code = p.product_code "
					+"and c.user_id = ? ";
		
		String cartvalues = "and c.cart_num in (";
		for(int i = 0; i < cartValues.length; i++) {
			cartvalues += cartValues[i];
			if(i != cartValues.length - 1) {
				cartvalues += ", ";
			}
		}
		cartvalues += ")";
		
		sql += cartvalues;
		
		System.out.println(sql);
		
		List<CartList> list = new ArrayList<CartList>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				CartList cartlist = new CartList();
				cartlist.setCart_num(rs.getInt("cart_num"));
				cartlist.setProduct_code(rs.getString("product_code"));
				cartlist.setUser_id(rs.getString("user_id"));
				cartlist.setOrder_de_count(rs.getInt("order_de_count"));
				cartlist.setAdddate(rs.getString("adddate"));
				cartlist.setProduct_name(rs.getString("product_name"));
				cartlist.setProduct_price(rs.getInt("product_price"));
				cartlist.setProduct_image(rs.getString("product_image"));
				list.add(cartlist);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getCartList() 에러 : " + ex);
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
	}
	
	
}
