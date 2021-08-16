package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public int insert(Member m) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			con = ds.getConnection();
			System.out.println("getConnection : insert()");
			
			pstmt = con.prepareStatement(
					"INSERT INTO member("
										+ "user_id, "
										+ "user_password, "
										+ "user_name, "
										+ "user_datebirth, "
										+ "user_gender, "
										+ "user_email, "
										+ "user_phone, "
										+ "user_address1, "
										+ "user_address2), "
										+ "memberfile, "
										+ "VALUES (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getUser_password());
			pstmt.setString(3, m.getUser_name());
			pstmt.setInt(4, m.getUser_datebirth());
			pstmt.setString(5, m.getUser_gender());
			pstmt.setString(6, m.getUser_email());
			pstmt.setString(7, m.getUser_phone());
			pstmt.setInt(8, m.getUser_address1());
			pstmt.setString(9, m.getUser_address2());
			pstmt.setString(10, m.getMemberfile());
			result = pstmt.executeUpdate();//삽입 성공 시 result는 1

		
		//primary key 제약조건 위반했을 경우 발생하는 에러
		} catch(java.sql.SQLIntegrityConstraintViolationException e) {
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
	}

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
	}

	public Member member_info(String user_id) {
		Member m = null;
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * from member where user_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setUser_id(rs.getString(1));
				m.setUser_password(rs.getString(2));
				m.setUser_name(rs.getString(3));
				m.setUser_datebirth(rs.getInt(4));
				m.setUser_gender(rs.getString(5));
				m.setUser_email(rs.getString(6));
				m.setUser_phone(rs.getString(7));
				m.setUser_address1(rs.getInt(8));
				m.setUser_address2(rs.getString(9));
				m.setMemberfile(rs.getString(10)); //<================추가
				System.out.println("====================== 1-1 " + m.toString());
			}
			System.out.println("====================== 2 ");

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
	}

	public int update(Member m) {
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
						+ "memberfile = ? "
						+ "where user_id = ?";	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getUser_name());
			//pstmt.setInt(2, m.getUser_jumin());
			pstmt.setString(2, m.getUser_gender());
			pstmt.setString(3, m.getUser_email());
			pstmt.setString(4, m.getUser_phone());
			pstmt.setInt(5, m.getUser_address1());
			pstmt.setString(6, m.getUser_address2());
			pstmt.setString(7, m.getMemberfile());
			pstmt.setString(8, m.getUser_id());
			result = pstmt.executeUpdate();
			System.out.println("====update 에러 체크 " + m.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update() 에러: " + e);

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
	}

	public int getListCount() {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int x = 0;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement("select count(*) from member where user_id != 'admin'");

			rs = pstmt.executeQuery();

			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("getListCount() 에러: " + ex);

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
	}

	public List<Member> getList(int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
					+ "	  from (select b.*, rownum rnum"
					+ "	  		from(select * from member "
					+ "				 where user_id != 'admin'"
					+ " 			 order by id) b	"
					+           ")"
					+ "   where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);

			int startrow = (page - 1) * limit + 1;
			
			int endrow = startrow + limit - 1;

			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

		
			while(rs.next()) {
				Member m = new Member();

				m.setUser_id(rs.getString("user_id"));
				m.setUser_password(rs.getString(2));
				m.setUser_name(rs.getString(3));
				m.setUser_datebirth(rs.getInt(4));
				m.setUser_gender(rs.getString(5));
				m.setUser_email(rs.getString(6));
				m.setUser_phone(rs.getString(7));
				m.setUser_address1(rs.getInt(8));
				m.setUser_address2(rs.getString(9));

				list.add(m);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("getlist() 에러1: " + ex);

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
	}

	public int getListCount(String field, String value) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from member "
					   + "where id !='admin' "
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

			System.out.println("getListCount() 에러2: " + ex);

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
	}

	public List<Member> getList(String field, String value, int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
					+ "	  from (select b.*, rownum rnum"
					+ "	  		from(select * from member "
					+ "				 where user_id != 'admin'"
					+ "              and " + field + " like ? "
					+ " 			 order by user_id) b"
					+ "			)"
					+ "   where rnum between ? and ?" ;
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
				m.setUser_password(rs.getString(2));
				m.setUser_name(rs.getString(3));
				m.setUser_datebirth(rs.getInt(4));
				m.setUser_gender(rs.getString(5));
				m.setUser_email(rs.getString(6));
				m.setUser_phone(rs.getString(7));
				m.setUser_address1(rs.getInt(8));
				m.setUser_address2(rs.getString(9));

				list.add(m);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("getListCount() 에러3: " + ex);

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
	}

	public int delete(String user_id) {
		Connection con=null;
		PreparedStatement pstmt = null;

		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "delete from member where user_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			result = pstmt.executeUpdate();
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
	}

	public int isId(String user_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int result=-1;//DB에 해당 id가 없습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select user_id from member where user_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id); //<=========java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1
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