package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

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
		MemberDAO mdao = new MemberDAO();
		String user_id = request.getParameter("checkPassword");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		boolean result = mdao.memberDelete(user_id);
		if(result == true) {
			out.println("<script>");
			out.println("alert('회원 탈퇴가 정상처리되었습니다.');");
			out.println("location.href='main.ma'");
			out.println("</script>");
			HttpSession session = request.getSession();
			session.invalidate();
		}else {
			out.println("<script>");
			out.println("alert('회원 탈퇴 실패입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
