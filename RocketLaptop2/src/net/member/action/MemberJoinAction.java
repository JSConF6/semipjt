package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.action.Action;
import net.main.action.ActionForward;

public class MemberJoinAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main/joinForm.jsp");
		return forward;
	}
}
