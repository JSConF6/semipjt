package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLoginAction implements Action {

	@Override
		public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws ServletException, IOException {
			System.out.println("여기는 login");
			String user_id = "";
			Cookie[] cookies = request.getCookies();
			if(cookies !=null ) {
				for(int i=0;i<cookies.length;i++) {
					if(cookies[i].getName().equals("user_id")) {
						user_id=cookies[i].getValue();
					}
				}
			}
			
			request.setAttribute("user_id", user_id);
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("member/loginForm.jsp");
			return forward;
		}

	}
