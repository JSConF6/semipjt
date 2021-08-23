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
		String product_code = request.getParameter("product_code");
		int order_de_count = Integer.parseInt(request.getParameter("order_de_count"));
		
		int check = 0;
		boolean result = false;
		
		cart.setUser_id(user_id);
		cart.setProduct_code(product_code);
		cart.setOrder_de_count(order_de_count);
		result = cdao.cartInsert(cart);
		if(result == true) {
			check = 1; // 장바구니 담기 성공
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(check);
		return null;
	}

}
