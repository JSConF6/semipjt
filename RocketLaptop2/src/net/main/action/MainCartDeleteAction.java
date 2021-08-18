package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.CartDAO;

public class MainCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartDAO cdao = new CartDAO();
		
		int cart_num = Integer.parseInt(request.getParameter("cart_num"));
		String user_id = request.getParameter("user_id");
		
		int result = cdao.cartDelete(cart_num, user_id);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(result);
		return null;
	}

}
