package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.CommentDAO;

public class CommentUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDAO dao = new CommentDAO();
		net.admin.db.Comment co = new net.admin.db.Comment();
		co.setContent(request.getParameter("content"));
		System.out.println("content=" + co.getContent());
		
		co.setNum(Integer.parseInt(request.getParameter("num")));

		int ok = dao.commentsUpdate(co);
		response.getWriter().print(ok);
		
		return null; // response로 보내는 것들은 반환 리턴 널
	}

}
