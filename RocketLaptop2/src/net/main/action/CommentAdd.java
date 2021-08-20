package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Comment;
import net.admin.db.CommentDAO;

public class CommentAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDAO dao = new CommentDAO();
		
		Comment co = new Comment();
		co.setUser_id(request.getParameter("user_id"));
		co.setContent(request.getParameter("content"));
		System.out.println("content=" + co.getContent());
		co.setComment_re_lev(Integer.parseInt(request.getParameter("comment_re_lev")));
		co.setComment_qna_num(Integer.parseInt(request.getParameter("comment_qna_num")));
		co.setComment_re_seq(Integer.parseInt(request.getParameter("comment_re_seq")));
		int ok = dao.commentsInsert(co);
		response.getWriter().print(ok);
		return null;
	}

}
