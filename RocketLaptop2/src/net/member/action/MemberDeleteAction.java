package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.main.action.Action;
import net.main.action.ActionForward;
import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		String user_id = request.getParameter("user_id");
		boolean result = mdao.memberDelete(user_id);
		if(result == false) {
			forward.setRedirect(false);
			request.setAttribute("maintitle", "회원 탈퇴");
			request.setAttribute("title", "회원 탈퇴");
			request.setAttribute("body", "회원 탈퇴 실패");
			request.setAttribute("path", "main.ma");
			forward.setPath("Modal/SuccessModal.jsp");
			return forward;
		}
		
		forward.setRedirect(false);
		request.setAttribute("maintitle", "회원 탈퇴");
		request.setAttribute("title", "회원 탈퇴");
		request.setAttribute("body", "회원 탈퇴 성공");
		request.setAttribute("path", "main.ma");
		forward.setPath("Modal/SuccessModal.jsp");
		HttpSession session = request.getSession();
		session.invalidate();
		return forward;
	}

}
