package net.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CommentDAO {
	// 데이터 베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
	DataSource ds;
	
	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public CommentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

		//글의 갯수 구하기
		public int getListCount(int BOARD_RE_REF) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs =null;
			int x = 0;
			String sql = "select count(*) "
					   + " from comm where comment_qna_num = ?";
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, BOARD_RE_REF);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
			} catch (Exception ex) {
				System.out.println("getListCount()_CommentDAO 에러1: " + ex);
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

	
	// 댓글 등록하기
	public int commentsInsert(Comment c) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = ds.getConnection();

			String sql = "insert into comm " 
					+ " values(comm_seq.nextval, ?, ?, sysdate, ?, ?, ?, comm_seq.nextval)";

			// 새로운 글을 등록하는 부분입니다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getUser_id());
			pstmt.setString(2, c.getContent());
			pstmt.setInt(3, c.getComment_qna_num());
			pstmt.setInt(4, c.getComment_re_lev());
			pstmt.setInt(5, c.getComment_re_seq());

			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("데이터삽입이 완료되었습니다.");
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

	
	//댓글 리스트 구하기
	public JsonArray getCommentList(int comment_qna_num, int state) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sort ="asc";
		if(state==2) {
			sort="desc";
		}
		
		String sql = "select comm.num, comm.user_id, comm.content, comm.reg_date, comm.comment_re_lev, "
				   + 		"comm.comment_re_seq, comm.comment_re_ref, member.user_memberfile "
				   + "from comm inner join member "
				   + 	  "on comm.user_id = member.user_id "
				   + "where comm.comment_qna_num = ? "
				   + "order by comm.comment_re_ref " + sort + ", "
				   + "comm.comment_re_seq asc";
		
		JsonArray array = new JsonArray();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_qna_num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("num", rs.getInt("num"));
				object.addProperty("user_id", rs.getString("user_id"));
				object.addProperty("content", rs.getString("content"));
				object.addProperty("reg_date", rs.getString("reg_date"));
				object.addProperty("comment_re_lev", rs.getInt("comment_re_lev"));
				object.addProperty("comment_re_seq", rs.getInt("comment_re_seq"));
				object.addProperty("comment_re_ref", rs.getInt("comment_re_ref"));
				object.addProperty("user_memberfile", rs.getString("user_memberfile"));
				array.add(object);
			}
		} catch (Exception ex) {
			System.out.println("getCommentList()_CommentDAO 에러2: " + ex);
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
		return array;	
	}// getCommentList() 메서드 end

	public int commentsUpdate(Comment co) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			String sql = "update comm set content=? "
					   + "where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getContent());
			pstmt.setInt(2, co.getNum());
			
			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("데이터 수정 되었습니다.");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("commentsUpdate 에러 3: " + ex);
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
	}// commentsUpdate() 메서드 end

	public int commentReply(Comment c) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			StringBuilder update_sql=new StringBuilder();
			update_sql.append("update comm ");
			update_sql.append("set    comment_re_seq=comment_re_seq +1 ");
			update_sql.append("where  comment_re_ref=? ");
			update_sql.append("and    comment_re_seq> ? ");
			pstmt = con.prepareStatement(update_sql.toString());
			pstmt.setInt(1, c.getComment_re_ref());
			pstmt.setInt(2, c.getComment_re_seq());
			pstmt.executeUpdate();
			pstmt.close();
			
			String sql = "insert into comm "
					+ " values(comm_seq.nextval, ?, ?, sysdate, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getUser_id());
			pstmt.setString(2, c.getContent());
			pstmt.setInt(3, c.getComment_qna_num());
			pstmt.setInt(4, c.getComment_re_lev()+1);
			pstmt.setInt(5, c.getComment_re_seq()+1);
			pstmt.setInt(6, c.getComment_re_ref());
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("reply 삽입 완료되었습니다.");
				con.commit();
			}else {
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback(); //rollback합니다.
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.setAutoCommit(true); //<=========================== 매우중요!!!!!!!!!!!!!!!!!!!!!!!!
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}	
		}
		return result;
	}//commentReply() end

	public int commentsDelete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			
			String sql = "delete comm where num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("데이터 삭제 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}	
		}	
		return result;
	}
}
