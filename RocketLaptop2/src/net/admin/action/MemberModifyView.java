package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		Member member = new Member();
		MemberDAO mdao = new MemberDAO();
		String user_id = request.getParameter("user_id");
		
		member = mdao.getMemberDetail(user_id);
		
		if(member == null) {
			System.out.println("(수정)상세보기 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "회원 수정 상세보기 실패입니다.");
			forward.setPath("error.error.jsp");
			return forward;
		}
		System.out.println("(수정)상세보기 성공");
		
		request.setAttribute("m", member);
		
		forward.setRedirect(false);
		forward.setPath("admin/memberModifyView.jsp");
		return forward;
	}

}
