package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource ds;
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	public int memberInsert(Member m) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			con = ds.getConnection();
			
			String sql = "insert into member "
					   + "( USER_ID, "
					   + "USER_PASSWORD, "
					   + "USER_NAME, "
					   + "USER_DATEBIRTH, "
					   + "USER_GENDER, "
					   + "USER_EMAIL, "
					   + "USER_PHONE, "
					   + "USER_ADDRESS1, "
					   + "USER_ADDRESS2, "
					   + "USER_MEMBERFILE,"
					   + "USER_JOINDATE )"
					   + "values("
					   + "?, "
					   + "?, "
					   + "?, "
					   + "?, "
					   + "?, "
					   + "?, "
					   + "?, "
					   + "?, "
					   + "?, "
					   + "?, "
					   + "sysdate )";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getUser_password());
			pstmt.setString(3, m.getUser_name());
			pstmt.setInt(4, m.getUser_birthdate());
			pstmt.setString(5, m.getUser_gender());
			pstmt.setString(6, m.getUser_email());
			pstmt.setString(7, m.getUser_phone());
			pstmt.setString(8, m.getUser_address1());
			pstmt.setString(9, m.getUser_address2());
			pstmt.setString(10, m.getUser_memberfile());
			
			result = pstmt.executeUpdate();

		
		//primary key 제약조건 위반했을 경우 발생하는 에러
		} catch(SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("유저 아이디 중복 에러입니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}	
		return result;
	} // memberInsert() end

	public int isId(String user_id, String user_password) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;

		int result=-1;
		try {
			con = ds.getConnection();
			
			String sql = "select user_id, user_password from member where user_id = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.println("user_id[" + rs.getString(1) +"]");
				System.out.println("user_password[" + rs.getString(2)+"]");
				if(rs.getString(2).equals(user_password)) {

					result = 1;	
				} else {
					result = 0; 

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}	
		return result;
	} // isId() end

	public Member getMemberDetail(String user_id) {
		Member m = null;
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select USER_ID," + 
					"USER_PASSWORD," + 
					"USER_NAME," + 
					"USER_DATEBIRTH," + 
					"USER_GENDER," + 
					"USER_EMAIL," + 
					"USER_PHONE," + 
					"USER_ADDRESS1," + 
					"USER_ADDRESS2," + 
					"USER_MEMBERFILE," + 
					"to_char(USER_JOINDATE, 'YYYYMMDD') as USER_JOINDATE"
					+ " from member where user_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setUser_id(rs.getString("user_id"));
				m.setUser_password(rs.getString("user_password"));
				m.setUser_name(rs.getString("user_name"));
				m.setUser_birthdate(rs.getInt("user_birthdate"));
				m.setUser_gender(rs.getString("user_gender"));
				m.setUser_email(rs.getString("user_email"));
				m.setUser_phone(rs.getString("user_phone"));
				m.setUser_address1(rs.getString("user_address1"));
				m.setUser_address2(rs.getString("user_address2"));
				m.setUser_memberfile(rs.getString("user_memberfile"));
				m.setUser_joindate(rs.getString("user_joindate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return m;	
	} // getMemberDetail() end

	public int memberUpdate(Member m) {
		Connection con=null;
		PreparedStatement pstmt = null;

		int result=0;
		try {
			con = ds.getConnection();
			
			String sql = "update member set "
						     + "user_name = ?, "
						     + "user_gender = ?, "
						     + "user_email = ?, "
						     + "user_phone = ?, "
						     + "user_address1 = ?, "
						     + "user_address2 = ?, "
						     + "user_memberfile = ? "
						     + "where user_id = ?";	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getUser_name());
			pstmt.setString(2, m.getUser_gender());
			pstmt.setString(3, m.getUser_email());
			pstmt.setString(4, m.getUser_phone());
			pstmt.setString(5, m.getUser_address1());
			pstmt.setString(6, m.getUser_address2());
			pstmt.setString(7, m.getUser_memberfile());
			pstmt.setString(8, m.getUser_id());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("memberUpdate() 에러: " + e);

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;	
	} // memberUpdate() end
	
	public boolean adminMemberModify(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update member "
					+"set USER_PASSWORD=?, USER_EMAIL=?, USER_PHONE=?, "
					+"USER_ADDRESS1=?, USER_ADDRESS2=? "
					+"where USER_ID=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUser_password());
			pstmt.setString(2, member.getUser_email());
			pstmt.setString(3, member.getUser_phone());
			pstmt.setString(4, member.getUser_address1());
			pstmt.setString(5, member.getUser_address2());
			pstmt.setString(6, member.getUser_id());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("성공 업데이트");
				return true;
			}
		}catch(SQLException ex){
			System.out.println("adminMemberModify() 에러 : " + ex);
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
	} // adminMemberModify() end

	public int getMemberListCount() {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from member "
					   + "where user_id !='admin'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("getMemberListCount() 에러: " + ex);

		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return x;
	} // getMemberListCount() end

	public List<Member> getMemberList(int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
						+"from (select b.*, rownum rnum "
						+ 	   "from(select * from member "
						+ 	   		"where user_id != 'admin' "
						+      		"order by user_id) b "
						+	   ")"
						+"where rnum>=? and rnum<=?";
			
			pstmt = con.prepareStatement(sql);
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;

			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setUser_id(rs.getString("user_id"));
				m.setUser_password(rs.getString("user_password"));
				m.setUser_name(rs.getString("user_name"));
				m.setUser_birthdate(rs.getInt("user_birthdate"));
				m.setUser_gender(rs.getString("user_gender"));
				m.setUser_email(rs.getString("user_email"));
				m.setUser_phone(rs.getString("user_phone"));
				m.setUser_address1(rs.getString("user_address1"));
				m.setUser_address2(rs.getString("user_address2"));
				m.setUser_memberfile(rs.getString("user_memberfile"));
				m.setUser_joindate(rs.getString("user_joindate"));
				list.add(m);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("getMemberList() 에러: " + ex);

		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return list;
	} // getMemberList() end

	public int getMemberListCount(String field, String value) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from member "
					   + "where user_id !='admin' "
					   + "and " + field + " like ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("getMemberListCount() 에러: " + ex);

		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return x;
	} // getMemberListCount() end

	public List<Member> getMemberList(String field, String value, int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
					        +"from (select b.*, rownum rnum "
					        +      "from(select * from member "
					        +      "where user_id != 'admin' "
					        +      "and " + field + " like ? "
					        +      "order by user_id) b "
					        +      ")"
					        +"where rnum between ? and ?" ;
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+value+ "%");
			int startrow = (page - 1) * limit + 1;
			
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(2, startrow);			
			pstmt.setInt(3, endrow);			

			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Member m = new Member();
				m.setUser_id(rs.getString("user_id"));
				m.setUser_password(rs.getString("user_password"));
				m.setUser_name(rs.getString("user_name"));
				m.setUser_birthdate(rs.getInt("user_birthdate"));
				m.setUser_gender(rs.getString("user_gender"));
				m.setUser_email(rs.getString("user_email"));
				m.setUser_phone(rs.getString("user_phone"));
				m.setUser_address1(rs.getString("user_address1"));
				m.setUser_address2(rs.getString("user_address2"));
				m.setUser_memberfile(rs.getString("user_memberfile"));
				m.setUser_joindate(rs.getString("user_joindate"));
				list.add(m);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("getMemberList() 에러: " + ex);

		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return list;
	} // getMemberList() end

	public boolean memberDelete(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete member "
				       + "where USER_ID=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("삭제 성공");
				return true;
			}
		}catch(SQLException ex){
			System.out.println("memberDelete() 에러 : " + ex);
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
	} // memberDelete() end

	public int isId(String user_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int result=-1;//DB에 해당 id가 없습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select user_id from member where user_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = 0; //DB에 해당 id가 있습니다.
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("isID 에러: " + ex);

		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
}	