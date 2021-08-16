package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		boolean result = false;
		
		String user_id = request.getParameter("user_id");
		System.out.println(user_id);
		
		result = mdao.memberDelete(user_id);
		
		if(result == false) {
			System.out.println("회원 삭제 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("회원 삭제 성공");
		forward.setRedirect(false);
		request.setAttribute("maintitle", "회원 삭제");
		request.setAttribute("title", "회원 삭제");
		request.setAttribute("body", "회원이 삭제되었습니다.");
		request.setAttribute("path", "MemberList.ad");
		forward.setPath("Modal/SuccessModal.jsp");
		return forward;
	}

}
