package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.QnaBean;
import net.admin.db.QnaDAO;

public class QnaReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		QnaDAO qdao = new QnaDAO(); // DB 연결을 위한 인스턴스 생성
		QnaBean qnadata = new QnaBean();  // 데이터 저장을 위해 빈(Bean) 인스턴스 생성
	   		
		// 답글을 남기기 위한 게시물 번호를 파라미터로 가져옵니다.
		int num = Integer.parseInt(request.getParameter("num"));
	   		
		qnadata = qdao.getDetail(num); // 게시물의 내용을 DAO 통해 가져옵니다.(메시지 오류의 세부 객체를 반환)
	   		
		if(qnadata == null){ // 게시물이 존재하지 않을 경우 아래 경고를 발생
			System.out.println("글이 존재하지 않습니다.");
			forward.setRedirect(false);
			request.setAttribute("message", "글이 존재하지 않습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("답변 페이지 이동완료");
		//답변 폼 페이지로 이동할 때 원본 글 내용을 보여주기 위해
		//boarddata객체를 Request 객체에 저장합니다.
		request.setAttribute("qnadata", qnadata);
		
		forward.setRedirect(false);
		//글 답변 페이지 경로 지정합니다.
		forward.setPath("main/qnaReply.jsp");
		return forward;
	} //execute end
}//class end