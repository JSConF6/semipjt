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

public class QnaDAO {
	private DataSource ds;
	
	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어 옵니다.
	public QnaDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	public int getQnaListCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from qna");
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getQnaListCount() 에러1: " + ex);
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
	} // getQnaListCount() end

	public List<QnaBean> getQnaList(int page, int limit) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		// page : 페이지
		// limit : 페이지당 목록의 수
		// BOARD_RE_REF desc, BOARD_RE_SEQ asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.
				
		String qna_list_sql = "select * from " 
				        + "     (select rownum rnum, QNA_NUM,  QNA_NAME,"
						+ "      QNA_SUBJECT,  QNA_CONTENT,  QNA_FILE," 
				        + "      QNA_RE_REF,   QNA_RE_LEV,   QNA_RE_SEQ,"
						+ "      QNA_READCOUNT, QNA_DATE"
						+ "		 from (select * from qna "
						+ "            order by QNA_RE_REF desc,"
						+ "            QNA_RE_SEQ asc) "
						+ "		) " 
						+ "where rnum>=? and rnum<=?";
		
		List<QnaBean> list = new ArrayList<QnaBean>();
		//한 페이지당 10개씩 목록인 경우				 1페이지, 2페이지, 3페이지, 4페이지....
		int startrow = (page - 1) * limit +1; //읽기 시작할 row번호(1 11 21 31...
		int endrow = startrow + limit -1;	//읽을 마지막 row번호(10 20 30 40...
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(qna_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				QnaBean qna = new QnaBean();
				qna.setQna_num(rs.getInt("QNA_NUM"));
				qna.setQna_name(rs.getString("QNA_NAME"));
				qna.setQna_subject(rs.getString("QNA_SUBJECT"));
				qna.setQna_content(rs.getNString("QNA_CONTENT"));
				qna.setQna_file(rs.getString("QNA_FILE"));
				qna.setQna_re_ref(rs.getInt("QNA_RE_REF"));
				qna.setQna_re_lev(rs.getInt("QNA_RE_LEV"));
				qna.setQna_re_seq(rs.getInt("QNA_RE_SEQ"));
				qna.setQna_readcount(rs.getInt("QNA_READCOUNT"));
				qna.setQna_date(rs.getString("QNA_DATE"));
				list.add(qna); // 값을 담은 객체를 리스트에 저장합니다.
		
			}
		
		} catch (Exception ex) {
			System.out.println("getQnaList() 에러2: " + ex);
			}finally {
				if (rs != null)
					try {
						rs.close();	
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
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
			return list;
	}//getQnaList()메서드 end

	public boolean qnaInsert(QnaBean qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			con = ds.getConnection();
			
			String max_sql = "(select nvl(max(qna_num),0)+1 from qna)";

			// 원문글의 BOARD_RE_REF 필드는 자신의 글번호 입니다.
			String sql = "insert into qna " 
			            + "(QNA_NUM,     QNA_NAME,  QNA_PASS,    QNA_SUBJECT,"
					    + " QNA_CONTENT, QNA_FILE,  QNA_RE_REF," 
			            + " QNA_RE_LEV,  QNA_RE_SEQ,QNA_READCOUNT)"
					    + " values(" + max_sql + ",?,?,?," 
			            + "        ?,?," +   max_sql  + "," 
					    + "        ?,?,?)";

			
			//새로운 글을 등록하는 부분입니다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getQna_name());
			pstmt.setString(2, qna.getQna_pass());
			pstmt.setString(3, qna.getQna_subject());
			pstmt.setString(4, qna.getQna_content());
			pstmt.setString(5, qna.getQna_file()); 
			
			//원문의 경우BOARD_RE_LEV,BOARD_RE_SEQ 필드 값은 0 입니다
			pstmt.setInt(6, 0);//BOARD_RE_LEV 필드
			pstmt.setInt(7, 0);//BOARD_RE_SEQ 필드
			pstmt.setInt(8, 0);//BOARD_READCOUNT 필드
			
			result = pstmt.executeUpdate(); //중요<==========
			if(result ==1) {
				System.out.println("데이터 삽입이 모두 완료 되었습니다.");
				return true;
			}
		} catch (Exception ex) {
			System.out.println("qnaInsert() 에러3: " + ex);
			ex.printStackTrace();
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
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return false;
	}//qnaInsert()메서드 end

	public void setReadCountUpdate(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update qna "
				   + "set QNA_READCOUNT=QNA_READCOUNT+1 "
				   + "where QNA_NUM = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate() 에러4: " + ex);
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
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
	}//setReadCountUpdate()메서드 end

	// 글 내용 보기
	public QnaBean getDetail(int num) {
		QnaBean qna = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from qna where QNA_NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qna = new QnaBean();
				qna.setQna_num(rs.getInt("QNA_NUM"));
				qna.setQna_name(rs.getString("QNA_NAME"));
				qna.setQna_subject(rs.getString("QNA_SUBJECT"));
				qna.setQna_content(rs.getString("QNA_CONTENT"));
				qna.setQna_file(rs.getString("QNA_FILE"));
				qna.setQna_re_ref(rs.getInt("QNA_RE_REF"));
				qna.setQna_re_lev(rs.getInt("QNA_RE_LEV"));
				qna.setQna_re_seq(rs.getInt("QNA_RE_SEQ"));
				qna.setQna_readcount(rs.getInt("QNA_READCOUNT"));
				qna.setQna_date(rs.getString("QNA_DATE"));
			}
		} catch (Exception ex) {
			System.out.println("getDetail() 에러 5: " + ex);
			}finally {
				if (rs != null)
					try {
						rs.close();	
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
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
		return qna;
	}//getDetail()메서드 end

	public int qnaReply(QnaBean qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num=0;
		
		// board 테이블의 글번호를 구하기 위해 board_num 필드의 최대값을 구해옵니다.
		String qna_max_sql = "select max(qna_num)+1 from qna";
		/*
		 * 답변을 달 원문 글 그룹 번호입니다.
		 * 답변을 달게 되면 답변글은 이 번호와 같은 관련글 번호를 갖게 처리되면서 같은 그룹에 속하게 됩니다.
		 * 글 목록에서 보여줄 때 하나의 그룹으로 묶여서 출력됩니다.
		 */
		int re_ref = qna.getQna_re_ref();
		
		/*
		 * 답글의 깊이를 의미합니다.
		 * 원문에 대한 답글이 출력될때 한 번 들여쓰기 처리가 되고 답글에 대한 답글은 들여쓰기가 두 번 처리 되게 합니다.
		 * 원문인 경우에는 이 값이 0이고 원문의 답글은 1, 답글의 답글은 2가 됩니다.
		 */
		
		int re_lev = qna.getQna_re_lev();
		
		// 같은 관련 글 중에서 해당 글이 출력되는 순서 입니다.
		int re_seq = qna.getQna_re_seq();
		
		try {
			con = ds.getConnection();
			
			// 트랜잭션을 이용하기 위해서 setAutoCommit을 false로 설정합니다.
			con.setAutoCommit(false);							// <=================== 특이점 확인
			pstmt=con.prepareStatement(qna_max_sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
			}
			pstmt.close();
			
			// BOARD_RE_REF, BOARD_RE_SEQ 값을 확인하여 원문글에 다른 답글이 있으면
			// 다른 답글들의 BOARD_RE_SEQ값을 1씩 증가시킵니다.
			// 현재 글을 다른 답글보다 앞에 출력되게 하기 위해서 입니다.
			String sql = "update qna "
					   + "set QNA_RE_SEQ=QNA_RE_SEQ +1 "
					   + "where QNA_RE_REF = ? "
					   + "and QNA_RE_SEQ > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			// 등록할 답변 글의 BOARD_RE_LEV, BOARD_RE_SEQ 값을 원문 글보다 1씩 증가시킵니다.
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			
			sql = "insert into qna " 
	            + "(QNA_NUM,     QNA_NAME,   QNA_PASS,    QNA_SUBJECT,"
			    + " QNA_CONTENT, QNA_FILE,   QNA_RE_REF," 
	            + " QNA_RE_LEV,  QNA_RE_SEQ, QNA_READCOUNT)"
			    + " values(" + num + "," 
	            + "       ?,?,?," 
	            + "       ?,?,?," 
			    + "       ?,?,?)";

			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getQna_name());
			pstmt.setString(2, qna.getQna_pass());
			pstmt.setString(3, qna.getQna_subject());
			pstmt.setString(4, qna.getQna_content());
			pstmt.setString(5, ""); //답변에는 파일을 업로드 하지 않습니다.
			pstmt.setInt(6, re_ref); //원문의 글번호
			pstmt.setInt(7, re_lev);			
			pstmt.setInt(8, re_seq);
			pstmt.setInt(9, 0); //BOARD_READCOUNT(조회수)는 0
			if(pstmt.executeUpdate() == 1) {
				con.commit(); //commit합니다.
			}else {
				con.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("qnaReply() 에러 6: " + ex);
			if(con != null) {
				try {
					con.rollback(); //rollback합니다.
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {
				if (rs != null)
					try {
						rs.close();	
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
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
		return num;
	}//qnaReply()메서드 end
	
	// 글쓴이인지 확인 - 비밀번호로 확인합니다
	public boolean isBoardWriter(int num, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;	//<==============================특이점
		String qna_sql = "select QNA_PASS from qna where QNA_NUM=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(qna_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if (pass.equals(rs.getString("QNA_PASS"))) {
					result = true;
				}
			}
		} catch (SQLException ex) {
			System.out.println("isBoardWriter() 에러 7: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();	
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
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
		}//isBoardWriter()메서드 end

	public boolean qnaModify(QnaBean modifyqna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update qna "
				   + "set QNA_SUBJECT=?, QNA_CONTENT=?, QNA_FILE=? "
				   + "where QNA_NUM=? ";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyqna.getQna_subject());
			pstmt.setString(2, modifyqna.getQna_content());
			pstmt.setString(3, modifyqna.getQna_file());
			pstmt.setInt(4, modifyqna.getQna_num());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("성공 업데이트");
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("qnaModify() 에러8: " + ex);
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
		return false;
	}//qnaModify()메서드 end

	public boolean qnaDelete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 = null;	//<================= 특이점
		ResultSet rs = null;
		String select_sql = "select QNA_RE_REF, QNA_RE_LEV, QNA_RE_SEQ "
						  + " from qna"
						  + " where QNA_NUM=?";
		
		String qna_delete_sql = "delete from qna"
				+"			 where	QNA_RE_REF = ?"
				+"			 and    QNA_RE_LEV >=?"
				+"			 and    QNA_RE_SEQ >=?"
				+"			 and    QNA_RE_SEQ <=("
				+"								  nvl((SELECT min(qna_re_seq )-1"
				+"								       FROM QNA "
				+"								       WHERE QNA_RE_REF=? "
				+"								       AND QNA_RE_LEV=?"
				+"								       AND QNA_RE_SEQ>?) , "
				+"								       (SELECT max(qna_re_seq) "
				+"								        FROM QNA "
				+"								        WHERE QNA_RE_REF=? ))"
				+"									)";	
	
		boolean result_check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt2 = con.prepareStatement(qna_delete_sql);
				pstmt2.setInt(1, rs.getInt("QNA_RE_REF"));
				pstmt2.setInt(2, rs.getInt("QNA_RE_LEV"));
				pstmt2.setInt(3, rs.getInt("QNA_RE_SEQ"));
				pstmt2.setInt(4, rs.getInt("QNA_RE_REF"));
				pstmt2.setInt(5, rs.getInt("QNA_RE_LEV"));
				pstmt2.setInt(6, rs.getInt("QNA_RE_SEQ"));
				pstmt2.setInt(7, rs.getInt("QNA_RE_REF"));
			
				int count=pstmt2.executeUpdate();
				
				if(count>=1)
					result_check = true; // 삭제가 안된 경우에는 false를 반환합니다.
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("qnaDelete() 에러 9: " + ex);	
		} finally {
			if (rs != null)
				try {
					rs.close();	
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		 }
		return result_check;
	}// qnaDelete()메서드 end

	
}// QnaDAO class end