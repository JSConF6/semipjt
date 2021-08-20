package net.main.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		net.admin.db.CommentDAO dao = new net.admin.db.CommentDAO();
		
		int result = dao.commentsDelete(num);
		PrintWriter out = response.getWriter();
		out.print(result);
		
		return null;
	}

}