package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		Member member = new Member();
		MemberDAO mdao = new MemberDAO();
		
		boolean result = false;
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		String user_email = request.getParameter("user_email");
		String user_phone = request.getParameter("user_phone");
		String user_address1 = request.getParameter("user_address1");
		String user_address2 = request.getParameter("user_address2");
		
		
		member.setUser_id(user_id);
		member.setUser_password(user_password);
		member.setUser_email(user_email);
		member.setUser_phone(user_phone);
		member.setUser_address1(user_address1);
		member.setUser_address2(user_address2);
		
		result = mdao.adminMemberModify(member);
		
		if(result == false) {
			System.out.println("회원정보 수정 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "회원정보가 수정 되지 않았습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("회원정보 수정 성공");
		forward.setRedirect(false);
		request.setAttribute("maintitle", "회원정보 수정");
		request.setAttribute("title", "회원정보 수정");
		request.setAttribute("body", "회원정보가 수정되었습니다.");
		request.setAttribute("path", "MemberDetail.ad?user_id=" + member.getUser_id());
		forward.setPath("Modal/SuccessModal.jsp");
		return forward;
	}

}
