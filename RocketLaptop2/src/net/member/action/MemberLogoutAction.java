package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.main.action.Action;
import net.main.action.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("여기는 logout");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		session.invalidate();
		forward.setPath("main.ma");
		forward.setRedirect(true);
		return forward;
	}

}
