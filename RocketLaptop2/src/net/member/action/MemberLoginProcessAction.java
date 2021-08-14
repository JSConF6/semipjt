package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.main.action.Action;
import net.main.action.ActionForward;
import net.member.db.MemberDAO;

public class MemberLoginProcessAction implements Action {

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ActionForward forward = new ActionForward();
			String user_id = request.getParameter("user_id");
			String user_password = request.getParameter("user_password");
			MemberDAO mdao = new MemberDAO();
			int result = mdao.isId(user_id,user_password);
			System.out.println("결과는" + result);
			
			//로그인 성공
			if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("user_id", user_id);
				
				String IDStore = request.getParameter("remember");
				Cookie cookie = new Cookie("user_id", user_id);
				
				// ID 기억하기를 체크한 경우
				if (IDStore != null && IDStore.equals("store")) {
					// cookie.setMaxAge( 60 *60 * 24); //쿠키의 유효시간을 24시간으로 설정합니다.
					cookie.setMaxAge(2*60);
					// 클라이언트로 쿠키값을 전송합니다.
					response.addCookie(cookie);
					
				} else {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				forward.setRedirect(true);
				forward.setPath("main.ma"); //<=====================향후 수정할 것
				return forward;
			} else {
				String message = "비밀번호가 일치하지 않습니다.";
				if (result == -1)
					message = "아이디가 존재하지 않습니다.";
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('" + message + "');");
				out.println("location.href='login.net';");
				out.println("</script>");
				out.close();
				return null;
			}
		}
	}
