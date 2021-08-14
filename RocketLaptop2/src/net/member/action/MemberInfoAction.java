package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.action.Action;
import net.main.action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String user_id = request.getParameter("user_id");
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(user_id);
		
		if(m==null) {
			forward.setPath("error/error.jsp");
			forward.setRedirect(false);
			request.setAttribute("message","아이디에 해당하는 정보가 없습니다.");
			return forward;
		}
		forward.setPath("member/memberInfo.jsp");
		forward.setRedirect(false);
		request.setAttribute("memberinfo", m);
		return forward;
	}

}
