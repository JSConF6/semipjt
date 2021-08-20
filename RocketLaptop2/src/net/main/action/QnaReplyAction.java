package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.QnaBean;
import net.admin.db.QnaDAO;

public class QnaReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		QnaDAO qdao=new QnaDAO();
		QnaBean qnadata=new QnaBean();
		int result=0;
		
		//파라미터로 넘어온 값들을 boarddata 객체에 저장합니다.
		qnadata.setQna_name(request.getParameter("qna_name"));
		qnadata.setQna_subject(request.getParameter("qna_subject"));
		qnadata.setQna_content(request.getParameter("qna_content"));
		qnadata.setQna_file(request.getParameter("qna_file"));
		qnadata.setQna_re_ref(Integer.parseInt(request.getParameter("qna_re_ref")));
		qnadata.setQna_re_lev(Integer.parseInt(request.getParameter("qna_re_lev")));
		qnadata.setQna_re_seq(Integer.parseInt(request.getParameter("qna_re_seq")));
		
		//답변을 DB에 저장하기 위해 boarddata 객체를 파라미터로 전달하고
		//DAO의 메서드 boardReply를 호출합니다.
		result=qdao.qnaReply(qnadata);
		
		//답변 저장에 실패한 경우
		if(result==0) {
			System.out.println("답장 저장 실패");
			forward = new ActionForward();
			forward.setRedirect(false); //포워딩 방식으로 주소가 바뀌지 않아요
			request.setAttribute("message", "답장 저장 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//답변 저장이 제대로 된 경우
		System.out.println("답장 완료");
		forward.setRedirect(true);
		//답변 글 내용을 확인하기 위해 글 내용 보기 페이지를 경로로 설정합니다.
		forward.setPath("QnaDetailAction.ma?num=" + result);
		return forward;
	}

}
