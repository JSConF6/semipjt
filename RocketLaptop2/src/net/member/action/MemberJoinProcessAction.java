package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

import net.main.action.Action;
import net.main.action.ActionForward;

public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		String user_name = request.getParameter("user_name");
		int user_birthdate = Integer.parseInt(request.getParameter("user_birthdate"));
		String user_gender = request.getParameter("user_gender");
		String user_email = request.getParameter("user_email");
		String user_phone = request.getParameter("user_phone");
		int user_address1 = Integer.parseInt(request.getParameter("user_address1"));
		String user_address2 = request.getParameter("user_address2");
		String user_memberfile = request.getParameter("user_memberfile");
		
		Member m = new Member();
		m.setUser_id(user_id); m.setUser_password(user_password); m.setUser_name(user_name);
		m.setUser_birthdate(user_birthdate); m.setUser_gender(user_gender); m.setUser_email(user_email);
		m.setUser_phone(user_phone); m.setUser_address1(user_address1); m.setUser_address2(user_address2);
		m.setUser_memberfile(user_memberfile);
		
		response.setContentType("text/html;charset=utf-8");;
		PrintWriter out = response.getWriter();
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.memberInsert(m);
		out.println("<script>>");
		if (result == 1) {
			out.println("alert('회원가입을 축하합니다.');");
			out.println("location.href='login.ma';");
		} else if (result == -1) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		
		return null;
	}

}
