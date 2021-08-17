package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.Cart;
import net.main.db.CartDAO;

public class MainCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartDAO cdao = new CartDAO();
		Cart cart = new Cart();
		
		String user_id = request.getParameter("user_id");
		System.out.println(user_id);
		String product_code = request.getParameter("product_code");
		int cart_stock = Integer.parseInt(request.getParameter("stock"));
		
		int checkid = 0;
		
		if(!user_id.equals("")) {
			cart.setUser_id(user_id);
			cart.setProduct_code(product_code);
			cart.setCart_stock(cart_stock);
			cdao.cartInsert(cart);
			checkid = 1;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(checkid);
		return null;
	}

}
