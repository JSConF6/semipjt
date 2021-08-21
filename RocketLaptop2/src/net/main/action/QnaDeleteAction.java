package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.QnaDAO;

public class QnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		boolean result=false;
		boolean usercheck=false;
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		QnaDAO qdao = new QnaDAO();
		//글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		//입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
		usercheck=qdao.isBoardWriter(num, request.getParameter("qna_pass"));
		
		//비밀번호 일치하지 않는 경우
		if(usercheck==false) {
			forward.setRedirect(false);
			forward.setPath("Modal/QnaModal.jsp");
			return forward;
		}
	
		// 비밀번호 일치하는 경우 삭제 처리합니다.
		result = qdao.qnaDelete(num);
		
		//삭제처리 실패한 경우
		if(result==false) {
			System.out.println("게시판 삭제 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		// 삭제처리 성공의 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
		System.out.println("게시판 삭제 성공");
		forward.setRedirect(false);
		forward.setPath("Modal/QnaDeleteModal.jsp");
		return forward;
	}
}
