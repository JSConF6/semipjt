package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO mdao = new MemberDAO();
		Member member = new Member();
		
		String user_id = request.getParameter("user_id");
		member = mdao.getMemberDetail(user_id);
		
		if(member == null) {
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		request.setAttribute("m", member);
		
		ActionForward forward = new ActionForward();		
		forward.setRedirect(false);
		forward.setPath("admin/memberDetailView.jsp");
		return forward;
	}

}
