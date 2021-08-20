package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); //포워딩 방식으로 주소가 바뀌지 않아요
		forward.setPath("main/qnaWrite.jsp");
		return forward;
	}
}	//class end
