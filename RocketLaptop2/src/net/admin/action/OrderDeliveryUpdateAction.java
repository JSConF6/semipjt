package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.OrderDAO;

public class OrderDeliveryUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderDAO odao = new OrderDAO();
		String deliveryStatus = request.getParameter("deliveryStatus");
		String user_id = request.getParameter("user_id");
		String order_num = request.getParameter("order_num");

		int result = 0;
		result = odao.orderDeliveryUpdate(order_num, user_id, deliveryStatus);
		
		response.setContentType("text/html/charset=UTF-8");
		response.getWriter().println(result);
		return null;
	}

}
