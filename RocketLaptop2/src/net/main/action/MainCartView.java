package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.CartDAO;
import net.main.db.CartList;

public class MainCartView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CartList> cartlist = new ArrayList<CartList>();
		CartDAO cdao = new CartDAO();
		
		String user_id = request.getParameter("user_id");
		
		int listcount = cdao.getCartListCount(user_id);
		cartlist = cdao.getCartList(user_id);
		
		request.setAttribute("cartlist", cartlist);
		request.setAttribute("listcount", listcount);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main/mainCartView.jsp");
		return forward;
	}

}
