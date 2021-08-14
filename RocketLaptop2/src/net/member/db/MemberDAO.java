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
	
	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어 옵니다.
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
										//+ "user_password1, "			//<=========삭제가 맞는거 같은데...
										+ "user_name, "
										//+ "user_jumin, "
										+ "user_gender, "
										+ "user_email, "
										+ "user_phone, "
										+ "user_address1, "
										+ "user_address2), "
										+ "memberfile, "
										+ "VALUES (?,?,?,?,?,?,?,?)");
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getUser_password());
			//pstmt.setString(3, m.getUser_password1());
			pstmt.setString(3, m.getUser_name());
			//pstmt.setInt(5, m.getUser_jumin());
			pstmt.setString(4, m.getUser_gender());
			pstmt.setString(5, m.getUser_email());
			pstmt.setInt(6, m.getUser_phone());
			pstmt.setInt(7, m.getUser_address1());
			pstmt.setString(8, m.getUser_address2());
			result = pstmt.executeUpdate();//삽입 성공 시 result는 1
		
		//primary key 제약조건 위반했을 경우 발생하는 에러
		} catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("멤버 아이디 중복 에러입니다.");
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
		int result=-1;//아이디가 존재하지 않습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select user_id, user_password from member where user_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(2).equals(user_password)) {
					result = 1;	//아이디와 비밀번호 일치하는 경우
				} else {
					result = 0; //비밀번호가 일치하지 않는 경우
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
		System.out.println("====================== 1 ");
		System.out.println("====================== user_id " + user_id);
		user_id = "1234";	//<==========================================테스트용, 동현씨 작업 후 삭제할 것
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
				//m.setUser_jumin(rs.getInt(5));
				m.setUser_gender(rs.getString(4));
				m.setUser_email(rs.getString(5));
				m.setUser_phone(rs.getInt(6));
				m.setUser_address1(rs.getInt(7));
				m.setUser_address2(rs.getString(8));
				m.setMemberfile(rs.getString(9)); //<================추가
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
		System.out.println("====update 에러 체크 " + m.toString());
		int result=0;
		try {
			con = ds.getConnection();
			
			String sql = "update member set "
						+ "user_name = ?, "
						//+ "user_jumin = ?, "
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
			pstmt.setInt(4, m.getUser_phone());
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
/*
	public int getListCount() {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from member where id != 'admin'");
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
					+ "				 where id != 'admin'"
					+ " 			 order by id) b	"
					+           ")"
					+ "   where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			// 한 페이지 당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
			int startrow = (page - 1) * limit + 1;
						// 읽기 시작할 row 번호 ( 1 11 21 31 ...
			int endrow = startrow + limit - 1;
						// 읽을 마지막 row 번호 (10 20 30 40 ...
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

		
			while(rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString(2));
				m.setName(rs.getString(3));
				m.setAge(rs.getInt(4));
				m.setGender(rs.getString(5));
				m.setEmail(rs.getString(6));
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
					+ "				 where id != 'admin'"
					+ "              and " + field + " like ? "
					+ " 			 order by id) b"
					+ "			)"
					+ "   where rnum between ? and ?" ;
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+value+ "%");
			int startrow = (page - 1) * limit + 1;
						// 읽기 시작할 row 번호 ( 1 11 21 31 ...
			int endrow = startrow + limit - 1;
						// 읽을 마지막 row 번호 (10 20 30 40 ...
			pstmt.setInt(2, startrow);			// <===================주의
			pstmt.setInt(3, endrow);			//<===============주의
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString(2));
				m.setName(rs.getString(3));
				m.setAge(rs.getInt(4));
				m.setGender(rs.getString(5));
				m.setEmail(rs.getString(6));
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
	*/

	public int delete(String user_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		user_id = "5678";
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

	/*
	public int isId(String id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int result=-1;//DB에 해당 id가 없습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select id from member where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); //<=========java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1
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
*/
}	