package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberFindPasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		Member member = new Member();
		
		String user_id = request.getParameter("findPassId");
		
		member = mdao.getMemberDetail(user_id);
		
		if(member == null) {
			forward.setRedirect(false);
			request.setAttribute("maintitle", "비밀번호 찾기");
			request.setAttribute("title", "비밀번호 찾기");
			request.setAttribute("body", "<h4>존재하는 아이디가 없습니다.</h4>");
			request.setAttribute("path", "main.ma");
			forward.setPath("Modal/FindPassModal.jsp");
			return forward;
		}
		
		String message = "<span style='font-size : 20px;'>" + member.getUser_id() + "님의 비밀번호는 " + member.getUser_password() + "입니다.</span>";
		forward.setRedirect(false);
		request.setAttribute("maintitle", "비밀번호 찾기");
		request.setAttribute("title", "비밀번호 찾기");
		request.setAttribute("body", message);
		request.setAttribute("path", "main.ma");
		forward.setPath("Modal/FindPassModal.jsp");
		return forward;
	}

}
