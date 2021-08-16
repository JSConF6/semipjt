package net.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CategoryDAO {
private DataSource ds;
	
	public CategoryDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public List<Category> getCategoryList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String category_list_sql = "select * from category";
		
		List<Category> list = new ArrayList<Category>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(category_list_sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				category.setCategory_code(rs.getString("category_code"));
				category.setCategory_name(rs.getString("category_name"));
				list.add(category);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getCategoryList() 에러 : " + ex);
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
	} // getCategoryList() end

	public int getCategoryListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select nvl(count(*), 0) + 1 " 
					+"from category";
		
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
			System.out.println("getCategoryListCount() 에러 : " + ex);
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
	} // getCategoryListCount() end

	public boolean categoryInsert(Category category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			con = ds.getConnection();
			
			String sql = "insert into category "
					   + "values(?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category.getCategory_code());
			pstmt.setString(2, category.getCategory_name());
			
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("데이터 삽입이 완료되었습니다.");
				return true;
			}
			
		}catch(Exception ex) {
			System.out.println("categoryInsert() 에러 : " + ex);
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
	} // categoryInsert() end

	public boolean categoryDelete(String category_code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			con = ds.getConnection();
			
			String sql = "delete category where category_code = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category_code);
			
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("데이터 삭제가 완료되었습니다.");
				return true;
			}
			
		}catch(Exception ex) {
			System.out.println("categoryDelete() 에러 : " + ex);
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
	} // categoryDelete() end
}
